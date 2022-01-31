import {ChangeDetectorRef, Component, Inject, Input} from '@angular/core';
import {LOCAL_STORAGE, StorageService} from "ngx-webstorage-service";
import {UserService} from "./shared/services/user/user.service";
import {AuthService} from "./shared/services/auth/auth.service";
import {Admin} from "./shared/models/user/admin.model";
import {timer as observableTimer} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'lms';
  isLogin: boolean = false;
  admin: Admin = this._authService.user;
  showAnimation: boolean = true;
  private _isShow: boolean = false;
  private loginCounter = 0;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService,
              public _authService: AuthService,
              public userService: UserService,
              private changeDetection: ChangeDetectorRef) {
    this.getCurrentUser();
    this._authService.userChange.subscribe((value) => {
      this.admin = value;
      this.isLogin = !!this.admin.uuid;
      this.changeDetection.detectChanges();
    });
  }

  getCurrentUser() {
    let uuid = this.storage.get('current user');
    this.showAnimation = !!uuid;
    if (!!uuid) {
      this.userService.getByUuid(uuid).subscribe(value => {
        this.admin = value;
        this.isLogin = !!this.admin.uuid;
      }, error => {
        const source = observableTimer(1000).subscribe(value => {
          this.loginCounter++;
          if (this.loginCounter < 5) {
            this.getCurrentUser();
          } else {
            this.loginCounter = 0;
            this._authService.logout();
          }
        });
      });
    } else {
      if (this._authService.isAuthenticated()) {
        this._authService.logout();
      }
    }
  }

  @Input()
  set isShow(isShow: boolean) {
    this._isShow = isShow;
  }

  get isShow(): boolean {
    return this._isShow;
  }
}
