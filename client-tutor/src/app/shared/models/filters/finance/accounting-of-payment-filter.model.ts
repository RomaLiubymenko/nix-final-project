import {IAbstractFilter} from "../abstract-filter.model";

export class AccountingOfPaymentFilter implements IAbstractFilter {

  constructor(
    public date?: Date,
    public amount?: string,
    public purpose?: string,
    public comment?: string,
    public tutorUuid?: string) {
  }
}
