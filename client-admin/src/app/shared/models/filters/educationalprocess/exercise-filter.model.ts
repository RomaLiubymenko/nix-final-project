import {IAbstractFilter} from "../abstract-filter.model";

export class ExerciseFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public date?: Date,
    public content?: string,
    public tutorUuid?: string,
    public subjectUuid?: string) {
  }
}
