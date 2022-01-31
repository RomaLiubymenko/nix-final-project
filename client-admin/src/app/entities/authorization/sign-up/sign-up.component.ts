import {Component, HostListener, OnInit, ViewChildren} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslocoService} from "@ngneat/transloco";
import {AuthService} from "../../../shared/services/auth/auth.service";
import {MessageService} from "primeng/api";
import {CredentialRepresentation, User} from "../../../shared/models/user/user.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'lms-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss', '../sign-in/sign-in.component.scss']
})
export class SignUpComponent implements OnInit {

  @ViewChildren('input') refInputs: { valid: boolean, validSave: boolean }[];

  key: string;
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private _authService: AuthService,
    private _router: Router,
    private route: ActivatedRoute,
    private translate: TranslocoService,
    public messageService: MessageService,
  ) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
    this.route.data.subscribe(({key}) => this.key = key);
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
    this._authService.signUp(this.createClient(), 'ADMIN', this.key).subscribe(() => {
      this.loading = false;
      this._router.navigate(['authorization', 'sign-in']);
    }, err => {
      this.loading = false;
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Invalid Credentials',
        life: 20000
      });
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
    client.credentials = [new CredentialRepresentation(this.getFormControls['password'].value)];
    client.password = this.getFormControls['password'].value;
    return client;
  }

}

