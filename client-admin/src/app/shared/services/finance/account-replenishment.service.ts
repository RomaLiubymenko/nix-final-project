import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {AccountReplenishment} from "../../models/finance/account-replenishment.model";

@Injectable({
  providedIn: 'root'
})
export class AccountReplenishmentService extends AbstractService<AccountReplenishment> {

  override resourceUrl = '/api/account-replenishments';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
