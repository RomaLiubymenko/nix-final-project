import {AbstractModel} from "./abstract.model";
import {Student} from "./user/student.model";


export interface IStudentGroup extends AbstractModel {
  name?: string;
  description?: string;
  groupType?: GroupType;
  startDate?: Date;
  endDate?: Date;
  isFormed?: boolean
  students?: Student[]
}

export class StudentGroup implements IStudentGroup {

  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
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
  INDIVIDUAL = 'INDIVIDUAL', MINI_GROUP = 'MINI_GROUP', GROUP = 'GROUP'
}


