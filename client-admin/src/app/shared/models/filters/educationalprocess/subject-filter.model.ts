import {IAbstractFilter} from "../abstract-filter.model";

export class SubjectFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public description?: string,
    public tutorUuid?: string) {
  }
}
