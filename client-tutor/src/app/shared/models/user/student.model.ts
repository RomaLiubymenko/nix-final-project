import {GenderType, IUser} from "./user.model";
import {StudentGroup} from "../educationalprocess/student-group.model";
import {AccountReplenishment} from "../finance/account-replenishment.model";
import {Course} from "../educationalprocess/course.model";
import {Account} from "../finance/account.model";
import {Attendance} from "../finance/attendance.model";
import {Lesson} from "../educationalprocess/lesson.model";
import {Exercise} from "../educationalprocess/exercise.model";
import {Report} from "../educationalprocess/report.model";

export class Student implements IUser {

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
    public accountReplenishments?: AccountReplenishment[],
    public attendances?: Attendance[],
    public lessons?: Lesson[],
    public studentGroups?: StudentGroup[],
    public exercises?: Exercise[],
    public courses?: Course[],
    public reports?: Report[],
    public account?: Account) {
  }
}
