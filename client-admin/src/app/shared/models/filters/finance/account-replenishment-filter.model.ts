import {IAbstractFilter} from "../abstract-filter.model";
import {ReplenishmentMethod} from "../../finance/account-replenishment.model";

export class AccountReplenishmentFilter implements IAbstractFilter {

  constructor(
    public date?: Date,
    public amount?: string,
    public payer?: string,
    public purpose?: string,
    public comment?: string,
    public replenishmentMethod?: ReplenishmentMethod,
    public studentUuid?: string) {
  }
}
