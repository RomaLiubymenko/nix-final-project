import {IAbstractFilter} from "../abstract-filter.model";

export class CourseFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public description?: string,
    public languageOfInstruction?: string,
    public knowledgeLevelOfCourse?: string,
    public startDate?: Date,
    public endDate?: Date,
    public isCompleted?: string) {
  }
}
