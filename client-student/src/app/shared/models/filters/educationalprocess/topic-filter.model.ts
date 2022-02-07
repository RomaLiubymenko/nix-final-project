import {IAbstractFilter} from "../abstract-filter.model";

export class TopicFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public description?: string,
    public length?: string,
    public subjectUuid?: string,
    public courseUuid?: string) {
  }
}
