import {SignInComponent} from './sign-in/sign-in.component';
import {Routes} from '@angular/router';
import {SignUpComponent} from './sign-up/sign-up.component';

export const authorizationRoute: Routes = [
  {
    path: 'sign-in',
    component: SignInComponent
  }, {
    path: 'sign-up',
    component: SignUpComponent
  }
];

