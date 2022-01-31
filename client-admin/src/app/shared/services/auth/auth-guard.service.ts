import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {
  }

  canActivate(route?: ActivatedRouteSnapshot, state?: RouterStateSnapshot): boolean {
    if (!this.auth.isAuthenticated()) {
      if (state) {
        this.router.navigate(['authorization/sign-in'], {queryParams: {returnUrl: state.url}});
      } else {
        this.router.navigate(['authorization/sign-in'], {queryParams: {returnUrl: ''}});

      }
      return false;
    }
    return true;
  }
}
