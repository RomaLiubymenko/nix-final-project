import {AbstractModel} from "../abstract.model";
import {IUser} from "../user/user.model";

export interface IAccount extends AbstractModel {
  name?: string;
  description?: string;
  accountChangedDate?: Date;
  balance?: number;
  isBlocked?: boolean;
  type?: AccountType;
  users?: IUser[];
  ownershipType?: AccountOwnershipType
}

export class Account implements IAccount {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public description?: string,
    public accountChangedDate?: Date,
    public balance?: number,
    public isBlocked?: boolean,
    public type?: AccountType,
    public ownershipType?: AccountOwnershipType,
    public users?: IUser[],
  ) {
  }
}

export enum AccountType {
  STANDARD = 'STANDARD',
  WITHDRAWAL = "WITHDRAWAL",
  REPLENISHMENT = "REPLENISHMENT"
}

export enum AccountOwnershipType {
  STUDENT = "STUDENT",
  TUTOR = "TUTOR",
  ADMIN = "ADMIN"
}

