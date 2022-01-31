import {AbstractModel} from "../abstract.model";
import {StudentGroup} from "../student-group.model";
import {AccountReplenishment} from "../account-replenishment.model";
import {Course} from "../course.model";
import {Account} from "../account.model";

export class CredentialRepresentation {
  public type: string = 'password';
  public temporary: boolean = false;
  public value: string;

  constructor(value: string, type: string = 'password', temporary: boolean = false) {
    this.value = value;
    this.temporary = temporary;
    this.type = type;
  }
}

export interface IUser extends AbstractModel {
  username?: string,
  firstName?: string;
  lastName?: string;
  email?: string,
  gender?: GenderType,
  birthDay?: Date;
  activated?: boolean;
  studentGroups?: StudentGroup[];
  accountReplenishments?: AccountReplenishment[];
  courses?: Course[];
  account?: Account;
  password?: string,
}

export class User implements IUser {

  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
    public username?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: GenderType,
    public birthDay?: Date,
    public activated?: boolean,
    public password?: string,
    public credentials?: CredentialRepresentation[],
  ) {
  }
}

export enum GenderType {
  MALE='MALE', FEMALE='FEMALE'
}


