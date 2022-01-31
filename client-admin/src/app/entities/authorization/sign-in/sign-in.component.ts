import {Component, HostListener, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LOCAL_STORAGE, StorageService} from 'ngx-webstorage-service';
import {UserService} from "../../../shared/services/user/user.service";
import {AuthService, Credentials} from "../../../shared/services/auth/auth.service";
import {MessageService} from 'primeng/api';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'lms-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  loading = false;
  loginForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    public router: Router,
    @Inject(LOCAL_STORAGE) private storage: StorageService,
    private route: ActivatedRoute,
    private messageService: MessageService,
    private _authService: AuthService,
    private _userService: UserService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    if (this.storage.get('token') && this.storage.get('current user')) {
      this.storage.remove('token');
      this.storage.remove('current user');
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
    this._authService.login(credentials).subscribe(token => {
      if (Object(token).hasOwnProperty('access_token')) {
        this._authService.getUserInfo(credentials, token.access_token).subscribe(info => {
          if (info.roles.includes('ADMIN')) {
            this._authService.writeDownTokenInfo(token);
            this._authService.setCurrentUser(this.loginForm.controls['username'].value);
            const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
            this.router.navigate([returnUrl]);
          } else {
            this._authService.removeCurrentUserFromLocalStorage();
            this.storage.clear();
            this.loading = false;
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Invalid Credentials',
              life: 20000
            });
          }
        }, () => {
          this.storage.clear();
          this.loading = false;
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Invalid Credentials',
            life: 20000
          });
        });
      }
      this.loading = false;
    }, () => {
      this.storage.clear();
      this.loading = false;
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Invalid Credentials',
        life: 20000
      });
    });
  }

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === 'Enter') {
      this.signIn();
    }
  }

  signUp() {
    this.router.navigate(['authorization', 'sign-up']);
  }
}
