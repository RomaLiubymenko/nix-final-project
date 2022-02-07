import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TranslocoService} from "@ngneat/transloco";
import {AuthService} from "../../../shared/services/auth/auth.service";
import {MessageService} from "primeng/api";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GenderType, User} from "../../../shared/models/user/user.model";
import {ISelectModal} from "../../../shared/models/select.model";

@Component({
  selector: 'sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  userType = 'TUTOR';
  genderTypeForSelect: ISelectModal[] = [];
  selectedGender: any;

  constructor(
    private formBuilder: FormBuilder,
    private _authService: AuthService,
    private _router: Router,
    private translocoService: TranslocoService,
    public messageService: MessageService,
  ) {
  }

  ngOnInit(): void {
    this.setGenderTypeForSelect();
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(4)]],
      gender: ['', Validators.required],
      birthDay: ['', Validators.required]
    });
  }

  @HostListener('document:keypress', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.code === 'Enter') {
      this.signUp();
    }
  }

  signUp() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    const client = this.createClient();
    this._authService.signUp(client, this.userType).subscribe({
      next: () => {
        this.loading = false;
        this._router.navigate(['authorization', 'sign-in']);
      },
      error: (err) => {
        this.loading = false;
        this.translocoService.langChanges$.subscribe(() => {
          if (err.error.status.toUpperCase() === 'CONFLICT') {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: this.translocoService.translate('EMAIL_ADDRESS_IS_ALREADY_IN_USE'),
              life: 20000
            });
          } else {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: this.translocoService.translate('SOMETHING_WENT_WRONG'),
              life: 20000
            });
          }
        });
      }
    });
  }

  get getFormControls() {
    return this.registerForm.controls;
  }

  createClient(): User {
    const client = new User();
    client.username = this.getFormControls['username'].value;
    client.firstName = this.getFormControls['firstName'].value;
    client.lastName = this.getFormControls['lastName'].value;
    client.email = this.getFormControls['email'].value;
    client.password = this.getFormControls['password'].value;
    client.gender = this.getFormControls['gender'].value;
    client.birthDay = this.getFormControls['birthDay'].value;
    return client;
  }

  setGenderTypeForSelect() {
    this.translocoService.langChanges$.subscribe(() => {
      this.genderTypeForSelect = [{
        name: this.translocoService.translate('MALE'),
        code: GenderType.MALE
      }, {
        name: this.translocoService.translate('FEMALE'),
        code: GenderType.FEMALE
      }];
    });
  }
}
