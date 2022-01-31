import {GenderType, IUser} from "./user.model";
import {StudentGroup} from "../student-group.model";
import {AccountReplenishment} from "../account-replenishment.model";
import {Course} from "../course.model";
import {Account} from "../account.model";

export class Student implements IUser {

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
    public studentGroups?: StudentGroup[],
    public accountReplenishments?: AccountReplenishment[],
    public courses?: Course[],
    public account?: Account) {
  }
}
