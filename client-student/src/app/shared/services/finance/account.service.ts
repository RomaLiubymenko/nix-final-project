import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Account} from "../../models/finance/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountService extends AbstractService<Account> {

  override resourceUrl = '/api/accounts';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
