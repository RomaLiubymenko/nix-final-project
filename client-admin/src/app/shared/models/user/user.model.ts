import {AbstractModel} from "../abstract.model";
import {StudentGroup} from "../educationalprocess/student-group.model";
import {AccountReplenishment} from "../finance/account-replenishment.model";
import {Course} from "../educationalprocess/course.model";
import {Account} from "../finance/account.model";
import {Role} from "./role.model";

export interface IUser extends AbstractModel {
  username?: string,
  firstName?: string;
  lastName?: string;
  email?: string,
  gender?: GenderType,
  birthDay?: string;
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
    public created?: Date,
    public updated?: Date,
    public username?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: GenderType,
    public birthDay?: string,
    public activated?: boolean,
    public password?: string,
    public role?: Role
  ) {
  }
}

export enum GenderType {
  MALE = 'MALE', FEMALE = 'FEMALE'
}


