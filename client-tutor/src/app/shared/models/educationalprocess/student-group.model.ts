import {AbstractModel} from "../abstract.model";
import {Student} from "../user/student.model";
import {Course} from "./course.model";
import {Lesson} from "./lesson.model";

export interface IStudentGroup extends AbstractModel {
  name?: string;
  description?: string;
  groupType?: GroupType;
  startDate?: Date;
  endDate?: Date;
  isFormed?: boolean
  students?: Student[],
  course?: Course;
  lessons?: Lesson[];
}

export class StudentGroup implements IStudentGroup {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public description?: string,
    public groupType?: GroupType,
    public startDate?: Date,
    public endDate?: Date,
    public isFormed?: boolean,
    public students?: Student[]) {
  }
}

export enum GroupType {
  INDIVIDUAL = 'INDIVIDUAL',
  MINI_GROUP = 'MINI_GROUP',
  GROUP = 'GROUP'
}


