import {GenderType, IUser} from "./user.model";
import {Course} from "../educationalprocess/course.model";
import {Account} from "../finance/account.model";
import {AccountingOfPayment} from "../finance/accounting-of-payment.model";
import {Report} from "../educationalprocess/report.model";
import {Lesson} from "../educationalprocess/lesson.model";
import {Subject} from "../educationalprocess/subject.model";
import {Exercise} from "../educationalprocess/exercise.model";

export class Tutor implements IUser {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public username?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: GenderType,
    public birthDay?: Date,
    public activated?: boolean,
    public status?: TutorStatus,
    public accountingOfPayments?: AccountingOfPayment[],
    public reports?: Report[],
    public courses?: Course[],
    public lessons?: Lesson[],
    public subjects?: Subject[],
    public exercises?: Exercise[],
    public account?: Account) {
  }
}

export enum TutorStatus {
  WORKING = 'WORKING',
  SICK = 'SICK',
  ON_VACATION = 'ON_VACATION',
  ON_UNSCHEDULED_VACATION = 'ON_UNSCHEDULED_VACATION',
  DAY_OFF = 'DAY_OFF',
  FIRED = 'FIRED'
}
