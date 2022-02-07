import {IAbstractFilter} from "../abstract-filter.model";
import {AttendanceStatus} from "../../finance/attendance.model";

export class AttendanceFilter implements IAbstractFilter {
  constructor(
    public status?: AttendanceStatus,
    public description?: string,
    public lessonUuid?: string,
    public studentUuid?: string,
    public paymentAmount?: string) {
  }
}




