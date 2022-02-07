import {AbstractModel} from "../abstract.model";
import {Student} from "../user/student.model";
import {Lesson} from "../educationalprocess/lesson.model";

export interface IAttendance extends AbstractModel {
  status?: AttendanceStatus;
  description?: string;
  lesson?: Lesson;
  student?: Student;
  paymentAmount?: number;
}

export class Attendance implements IAttendance {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public status?: AttendanceStatus,
    public description?: string,
    public lesson?: Lesson,
    public student?: Student,
    public paymentAmount?: number,
  ) {
  }
}

export enum AttendanceStatus {
  PENDING = "PENDING",
  PRESENT = "PRESENT",
  ABSENCE_FOR_GOOD_REASON = "ABSENCE_FOR_GOOD_REASON",
  ABSENCE_FOR_NO_VALID_REASON = "ABSENCE_FOR_NO_VALID_REASON"
}
