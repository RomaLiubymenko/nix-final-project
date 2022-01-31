import {AbstractModel} from "./abstract.model";
import {Student} from "./user/student.model";

export interface ICourse extends AbstractModel {
  name?: string;
  description?: string;
  languageOfInstruction?: string;
  knowledgeLevelOfCourse?: string;
  startDate?: Date;
  endDate?: Date;
  isCompleted?: boolean;
  students?: Student[];
}

export class Course implements ICourse {

  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
    public name?: string,
    public description?: string,
    public languageOfInstruction?: string,
    public knowledgeLevelOfCourse?: string,
    public startDate?: Date,
    public endDate?: Date,
    public isCompleted?: boolean,
    public students?: Student[]
  ) {
  }
}
