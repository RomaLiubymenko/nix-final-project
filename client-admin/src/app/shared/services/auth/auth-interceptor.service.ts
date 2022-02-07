import {Inject, Injectable} from "@angular/core";
import {HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";
import {LOCAL_STORAGE, StorageService} from "ngx-webstorage-service";
import {Observable} from "rxjs";
import {AccessTokenRepresentation} from "../../models/auth/access-token-representation.model";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private _httpClient: HttpClient,
    private _authService: AuthService,
    private _router: Router,
    @Inject(LOCAL_STORAGE) private storage: StorageService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let clone: HttpRequest<any> | null = null;
    if (!!this.storage.get('token')) {
      if (this._authService.isTokenExpired()) {
        if (!(typeof (request.body) === 'string' && request.body.includes('grant_type=refresh_token'))) {
          if (!request.url.includes('auth/keycloak-server-info')) {
            const token: AccessTokenRepresentation = this.storage.get('token');
            clone = request.clone({headers: request.headers.set('Authorization', `Bearer ${token.access_token}`)});
            return next.handle(clone);
          }
        }
      }
      if (!this._authService.isTokenExpired()) {
        if (!request.url.includes('auth/keycloak-server-info')) {
          const token: AccessTokenRepresentation = this.storage.get('token');
          clone = request.clone({headers: request.headers.set('Authorization', `Bearer ${token.access_token}`)});
          return next.handle(clone);
        }
      }
    }
    return next.handle(request);
  }
}
