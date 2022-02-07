import {EventEmitter, Inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, of, timer as observableTimer} from 'rxjs';
import {environment} from "../../../../environments/environment";
import {UserService} from '../user/user.service';
import {User} from "../../models/user/user.model";
import {LOCAL_STORAGE, StorageService} from "ngx-webstorage-service";
import {CustomHttpParamEncoder} from "../../utils/request/custom-http-param-encoder";
import {httpOptions} from "../../utils/request/http-body-constant";
import {AccessTokenRepresentation} from "../../models/auth/access-token-representation.model";
import {KeycloakInfo} from "../../models/auth/keycloak-info.model";
import {Credentials} from "../../models/auth/credentials.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _user: User = new User();
  public refreshSubscription: any;
  private _accessToken: AccessTokenRepresentation;
  private _expiresIn: number;
  private _refreshExpiresIn: number;
  private _issuedAt: number;
  private _keycloakInfo: KeycloakInfo = new KeycloakInfo();
  userChange = new EventEmitter();
  private _signUpUrl = `${environment.apiUrl}/api/v1/auth/signup`;
  private _keycloakInfoUrl: string = `${environment.apiUrl}/api/v1/auth/keycloak-info`;
  private _keycloakTokenUrl: string;
  private _keycloakUserInfoUrl: string;

  constructor(
    @Inject(LOCAL_STORAGE) private storage: StorageService,
    private _httpClient: HttpClient,
    private _router: Router,
    private userService: UserService) {

    let token: AccessTokenRepresentation = this.storage.get('token');
    if (!!token) {
      this.writeDownActualTokenInfo(token);
    }
  }

  get keycloakInfo(): KeycloakInfo {
    return this._keycloakInfo;
  }

  set keycloakInfo(value: KeycloakInfo) {
    this._keycloakInfo = value;
  }

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
    setTimeout(() => {
      this.userChange.emit(this.user);
    }, 100);
  }

  get accessToken(): AccessTokenRepresentation {
    return this._accessToken;
  }

  set accessToken(value: AccessTokenRepresentation) {
    this._accessToken = value;
  }

  get expiresIn(): number {
    return this._expiresIn;
  }

  set expiresIn(value: number) {
    this._expiresIn = value;
  }

  get refreshExpiresIn(): number {
    return this._refreshExpiresIn;
  }

  set refreshExpiresIn(value: number) {
    this._refreshExpiresIn = value;
  }

  get issuedAt(): number {
    return this._issuedAt;
  }

  set issuedAt(value: number) {
    this._issuedAt = value;
  }

  public initKeycloakServerInfo(): void {
    this._httpClient.get<KeycloakInfo>(this._keycloakInfoUrl).subscribe((value) => {
      this.keycloakInfo = value;
      this._keycloakTokenUrl = `${this.keycloakInfo.authServerUrl}realms/${this.keycloakInfo.realm}/protocol/openid-connect/token`;
      this._keycloakUserInfoUrl = `${this.keycloakInfo.authServerUrl}realms/${this.keycloakInfo.realm}/protocol/openid-connect/userinfo`;
    });
  }

  public login(credentials: Credentials): Observable<any> {
    return this._httpClient.post<AccessTokenRepresentation>(this._keycloakTokenUrl,
      `username=${credentials.username}&password=${credentials.password}&grant_type=${this.keycloakInfo.grantType}&client_id=${this.keycloakInfo.clientId}`, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        }
      });
  }

  public writeDownTokenInfo(token: AccessTokenRepresentation): void {
    this._accessToken = token;
    this._accessToken.access_token = token.access_token;
    this._expiresIn = new Date((new Date().getTime() + (token.expires_in * 1000))).getTime();
    token.expires_in_date = new Date(this._expiresIn);
    this.refreshExpiresIn = new Date((new Date().getTime() + (token.refresh_expires_in * 1000))).getTime();
    token.refresh_expires_in_date = new Date(this.refreshExpiresIn);
    this._issuedAt = new Date().getTime();
    token.issued_at = this._issuedAt
    this.storage.set('token', token);
    this.startupTokenRefresh();
  }

  public writeDownActualTokenInfo(token: AccessTokenRepresentation): void {
    this._accessToken = token;
    this._accessToken.access_token = token.access_token;
    this._expiresIn = new Date(token.expires_in_date).getTime();
    this.refreshExpiresIn = new Date(token.refresh_expires_in_date).getTime();
    this._issuedAt = token.issued_at;
    this.startupTokenRefresh();
  }

  public setCurrentUser(username: string) {
    this.userService.getUserByUsername(username).subscribe((value) => {
      this.storage.set('user', value.uuid);
      this.user = value;
    });
  }

  public removeCurrentUserFromLocalStorage() {
    this.storage.remove('user')
  }

  public getUserInfo(credentials: Credentials, accessToken: string): Observable<any> {
    return this._httpClient.post<AccessTokenRepresentation>(this._keycloakUserInfoUrl, null, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': `Bearer ${accessToken}`
      }
    });
  }

  public refreshToken(): Observable<any> {
    const value: AccessTokenRepresentation = this.storage.get('token');
    if (!!value) {
      let refreshTokenRequest: Observable<any>;
      if (Object.keys(this._keycloakInfo).length == 0) {
        this._httpClient.get<KeycloakInfo>(this._keycloakInfoUrl).subscribe((info: KeycloakInfo) => {
          refreshTokenRequest = this._httpClient.post<AccessTokenRepresentation>(`${info.authServerUrl}realms/${info.realm}/protocol/openid-connect/token`,
            `grant_type=refresh_token&client_id=${info.clientId}&refresh_token=${value.refresh_token}`, {
              headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            });
        });
      } else {
        refreshTokenRequest = this._httpClient.post<AccessTokenRepresentation>(this._keycloakTokenUrl,
          `grant_type=refresh_token&client_id=${this.keycloakInfo.clientId}&refresh_token=${value.refresh_token}`, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
          });
      }
      refreshTokenRequest!.subscribe((value1: AccessTokenRepresentation) => {
        this._accessToken = value1;
        this._expiresIn = new Date((new Date().getTime() + (value1.expires_in * 1000))).getTime();
        this.refreshExpiresIn = new Date((new Date().getTime() + (value1.refresh_expires_in * 1000))).getTime();
        this._issuedAt = new Date().getTime();
      });
      return refreshTokenRequest!;
    }
    return of(null);
  }

  public startupTokenRefresh(): void {
    if (this.isAuthenticated()) {
      // Get the expiry time to generate
      // a delay in milliseconds
      const now: number = new Date().valueOf();
      const delay: number = (this._expiresIn) - now;
      // Use the delay in a timer to
      // run the refresh at the proper time
      const source = observableTimer(delay);
      // Once the delay time from above is
      // reached, get a new JWT and schedule
      // additional refreshes
      source.subscribe(() => {
        if (Object.keys(this._keycloakInfo).length == 0) {
          this._httpClient.get<KeycloakInfo>(this._keycloakInfoUrl).subscribe((value) => {
            this._keycloakInfo = value;
            this.getNewToken();
          });
        } else {
          this.getNewToken();
        }
      });
    }
  }

  public getToken(): string {
    return this.storage.get('token').access_token;
  }

  public isTokenExpired() {
    const token: AccessTokenRepresentation = this.storage.get('token');
    if (!token) {
      return true;
    }
    return !this._accessToken || new Date().getTime() > this._expiresIn;
  }

  public isRefreshTokenExpired() {
    const token: AccessTokenRepresentation = this.storage.get('token');
    if (!token) {
      return true;
    }
    return !token || new Date().getTime() > this.refreshExpiresIn;
  }

  public isAuthenticated() {
    return !!this.storage.get('token') && !this.isRefreshTokenExpired();
  }

  public unscheduledRefresh(): void {
    if (this.refreshSubscription) {
      this.refreshSubscription.unsubscribe();
    }
  }

  public getNewToken(): void {
    if (this.isAuthenticated()) {
      this.refreshToken().subscribe({
        next: data => this.writeDownTokenInfo(data),
        error: () => this.logout()
      });
    } else {
      this.logout();
    }
  }

  public logout() {
    this.storage.remove('token');
    this.storage.remove('user');
    this.unscheduledRefresh();
    console.log(this._router.url)
    window.location.href = this._router.url;
  }

  signUp(client: User, userType: string): Observable<any> {
    const params = new HttpParams({encoder: new CustomHttpParamEncoder()}).set('user-type', userType.toUpperCase());
    return this._httpClient.post(this._signUpUrl, client, {
      headers: httpOptions["headers"],
      params: params
    });
  }
}
