import {IAbstractFilter} from "../abstract-filter.model";

export class ReportFilter implements IAbstractFilter {

  constructor(
    public content?: string,
    public date?: Date,
    public comment?: string,
    public tutorUuid?: string,
    public studentUuid?: string) {
  }
}
