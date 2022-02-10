import { TutorStatus } from "../../user/tutor.model";
import {IAbstractFilter} from "../abstract-filter.model";

export class TutorFilter implements IAbstractFilter {

  constructor(
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: string,
    public birthDay?: string,
    public activated?: string,
    public status?: TutorStatus,
    public subjectUuids?: string[]) {
  }
}
