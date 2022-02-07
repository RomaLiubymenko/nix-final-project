import {IAbstractFilter} from "../abstract-filter.model";

export class StudentFilter implements IAbstractFilter {

  constructor(
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: string,
    public birthDay?: string,
    public activated?: string,
    public studentGroupUuids?: string[]) {
  }
}
