import {Component, HostListener, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LOCAL_STORAGE, StorageService} from 'ngx-webstorage-service';
import {UserService} from "../../../shared/services/user/user.service";
import {AuthService} from "../../../shared/services/auth/auth.service";
import {MessageService} from 'primeng/api';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Credentials} from "../../../shared/models/auth/credentials.model";
import {TranslocoService} from "@ngneat/transloco";

@Component({
  selector: 'sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  loading = false;
  submitted = false;
  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    public router: Router,
    @Inject(LOCAL_STORAGE) private storage: StorageService,
    private route: ActivatedRoute,
    private translocoService: TranslocoService,
    private messageService: MessageService,
    private _authService: AuthService,
    private _userService: UserService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    if (this.storage.get('token') && this.storage.get('user')) {
      this.storage.remove('token');
      this.storage.remove('user');
    }
    this._authService.initKeycloakServerInfo();
  }

  get formControls() {
    return this.loginForm.controls;
  }

  signIn() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.loading = true;
    const credentials = new Credentials(this.loginForm.controls['username'].value, this.loginForm.controls['password'].value);
    this.translocoService.langChanges$.subscribe(() => {
      this._authService.login(credentials).subscribe({
        next: token => {
          if (Object(token).hasOwnProperty('access_token')) {
            this._authService.getUserInfo(credentials, token.access_token).subscribe({
              next: info => {
                if (info.roles.includes('STUDENT')) {
                  this._authService.writeDownTokenInfo(token);
                  this._authService.setCurrentUser(this.loginForm.controls['username'].value);
                  const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
                  this.router.navigateByUrl(returnUrl);
                } else {
                  this._authService.removeCurrentUserFromLocalStorage();
                  this.storage.clear();
                  this.loading = false;
                  this.showErrorMessage(this.translocoService.translate('LOGIN_ACCESS_DENIED'));
                }
              },
              error: () => {
                this.storage.clear();
                this.loading = false;
                this.showErrorMessage(this.translocoService.translate('SOMETHING_WENT_WRONG'));
              }
            });
          }
          this.loading = false;
        },
        error: () => {
          this.storage.clear();
          this.loading = false;
          this.showErrorMessage(this.translocoService.translate('INVALID_LOGIN_OR_PASSWORD'));
        }
      })
    });
  }

  showErrorMessage(message: string) {
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: message,
      life: 20000
    });
  }

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === 'Enter') {
      this.signIn();
    }
  }
}
