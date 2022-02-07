import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable} from "rxjs";
import {Admin} from "../../shared/models/user/admin.model";
import {AdminService} from "../../shared/services/user/admin.service";
import {AdminFullInfoComponent} from "./admin-full-info/admin-full-info.component";

@Injectable({providedIn: 'root'})
export class AdminResolve implements Resolve<Admin> {

  constructor(private service: AdminService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Admin> {
    const uuid = route.params["uuid"];
    return this.service.getByUuid(uuid);
  }
}

export const adminRoute: Routes = [
  {
    path: ':uuid/full-info',
    component: AdminFullInfoComponent,
    data: {
      breadcrumb: 'ADMIN_FULL_INFO',
    },
    resolve: {
      admin: AdminResolve,
    }
  }
];
