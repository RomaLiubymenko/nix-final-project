import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {AccountingOfPayment} from "../../models/finance/accounting-of-payment.model";

@Injectable({
  providedIn: 'root'
})
export class AccountingOfPaymentService extends AbstractService<AccountingOfPayment> {

  override resourceUrl = '/api/accounting-of-payments';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
