import {AbstractModel} from "../abstract.model";
import {Tutor} from "../user/tutor.model";
import {Student} from "../user/student.model";
import {Grade} from "./grade.model";
import {Exercise} from "./exercise.model";

export interface IReport extends AbstractModel {
  content?: string;
  date?: Date;
  comment?: string;
  tutor?: Tutor;
  student?: Student;
  grade?: Grade[];
  exercises?: Exercise[];
}

export class Report implements IReport {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public content?: string,
    public date?: Date,
    public comment?: string,
    public tutor?: Tutor,
    public student?: Student,
    public grade?: Grade[],
    public exercises?: Exercise[]) {
  }
}
