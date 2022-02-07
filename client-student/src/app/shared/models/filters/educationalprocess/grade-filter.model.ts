import {IAbstractFilter} from "../abstract-filter.model";

export class GradeFilter implements IAbstractFilter {

  constructor(
    public value?: string,
    public weight?: string,
    public date?: Date,
    public studentUuid?: string,
    public tutorUuid?: string) {
  }
}
