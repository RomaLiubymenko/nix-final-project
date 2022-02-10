import {IAbstractFilter} from "../abstract-filter.model";
import {AccountOwnershipType, AccountType} from "../../finance/account.model";

export class AccountFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public description?: string,
    public accountChangedDate?: string,
    public balance?: string,
    public isBlocked?: string,
    public type?: AccountType,
    public ownershipType?: AccountOwnershipType,
    public userUuids?: string[],
  ) {
  }
}
