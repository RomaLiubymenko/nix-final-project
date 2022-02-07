import {IAbstractFilter} from "../abstract-filter.model";
import {LessonStatus, LessonType} from "../../educationalprocess/lesson.model";

export class LessonFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public description?: string,
    public date?: Date,
    public length?: string,
    public lessonType?: LessonType,
    public lessonStatus?: LessonStatus,
    public subjectUuid?: string,
    public roomUuid?: string) {
  }
}
