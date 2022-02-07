import {ChangeDetectorRef, Component, Inject} from '@angular/core';
import {LOCAL_STORAGE, StorageService} from "ngx-webstorage-service";
import {UserService} from "./shared/services/user/user.service";
import {AuthService} from "./shared/services/auth/auth.service";
import {timer as observableTimer} from 'rxjs';
import {Tutor} from "./shared/models/user/tutor.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isLogin: boolean = false;
  tutor: Tutor = this._authService.user;
  private loginCounter = 0;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService,
              public _authService: AuthService,
              public userService: UserService,
              private changeDetection: ChangeDetectorRef) {
    this.getCurrentUser();
    this._authService.userChange.subscribe((value) => {
      this.tutor = value;
      this.isLogin = !!this.tutor.uuid;
      this.changeDetection.detectChanges();
    });
  }

  getCurrentUser() {
    let uuid = this.storage.get('user');
    if (!!uuid) {
      this.userService.getByUuid(uuid).subscribe({
        next: (data) => {
          this.tutor = data;
          this.isLogin = !!this.tutor.uuid;
        },
        error: () => {
          observableTimer(1000).subscribe(() => {
            this.loginCounter++;
            if (this.loginCounter < 5) {
              this.getCurrentUser();
            } else {
              this.loginCounter = 0;
              this._authService.logout();
            }
          });
        }
      });
    } else {
      if (this._authService.isAuthenticated()) {
        this._authService.logout();
      }
    }
  }
}
