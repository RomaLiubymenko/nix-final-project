import {IAbstractFilter} from "../abstract-filter.model";
import {GroupType} from "../../educationalprocess/student-group.model";

export class StudentGroupFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public description?: string,
    public groupType?: GroupType,
    public startDate?: string,
    public endDate?: string,
    public isFormed?: string,
    public studentUuids?: string[]) {
  }
}




