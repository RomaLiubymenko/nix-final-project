import {AbstractModel} from "../abstract.model";
import {Course} from "./course.model";
import {Report} from "./report.model";

export interface IGrade extends AbstractModel {
  value?: string;
  weight?: number;
  date?: Date;
  report?: Report;
  course?: Course;
}

export class Grade implements IGrade {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public value?: string,
    public weight?: number,
    public date?: Date,
    public report?: Report,
    public course?: Course) {
  }
}
