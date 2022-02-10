import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable, of} from "rxjs";
import {Account} from "src/app/shared/models/finance/account.model";
import {AccountService} from "../../../shared/services/finance/account.service";
import {AccountEditComponent} from "./account-edit/account-edit.component";
import {AccountTableComponent} from "./account-table/account-table.component";

@Injectable({providedIn: 'root'})
export class AccountResolve implements Resolve<Account> {

  constructor(private service: AccountService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<any> {
    const uuid = route.params["uuid"];
    if (uuid) {
      return of({
        account: this.service.getByUuid(uuid),
        userType: route.params['queryParams']
      });
    }
    return of({
      account: of(new Account()),
      userType: route.params['queryParams']
    });
  }
}

export const accountRoute: Routes = [
  {
    path: '',
    component: AccountTableComponent,
    data: {
      defaultSort: 'created,asc',
    },
  },
  {
    path: ':uuid/edit-form',
    component: AccountEditComponent,
    data: {
      breadcrumb: 'ACCOUNT',
    },
    resolve: {
      data: AccountResolve,
    }
  },
  {
    path: 'new-account',
    component: AccountEditComponent,
    data: {
      breadcrumb: 'ACCOUNT'
    },
    resolve: {
      data: AccountResolve,
    }
  }
];
