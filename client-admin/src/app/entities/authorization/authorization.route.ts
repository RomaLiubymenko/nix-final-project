import {Injectable} from '@angular/core';
import {SignInComponent} from './sign-in/sign-in.component';
import {ActivatedRouteSnapshot, Resolve, Routes} from '@angular/router';
import {SignUpComponent} from './sign-up/sign-up.component';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class AuthorizationResolve implements Resolve<string> {

  constructor() {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<string> {
    return route.params['key'];
  }
}

export const authorizationRoute: Routes = [
  {
    path: 'sign-in',
    component: SignInComponent,
    data: {}
  }, {
    path: 'sign-up',
    component: SignUpComponent,
    data: {}
  }, {
    path: 'sign-up/:key',
    component: SignUpComponent,
    data: {},
    resolve: {
      key: AuthorizationResolve,
    }
  }
];

