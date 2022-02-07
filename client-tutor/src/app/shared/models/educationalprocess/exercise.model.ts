import {AbstractModel} from "../abstract.model";
import {Tutor} from "../user/tutor.model";
import {Course} from "./course.model";
import {Report} from "./report.model";
import {Student} from "../user/student.model";
import {Topic} from "./topic.model";
import {Subject} from "./subject.model";

export interface IExercise extends AbstractModel {
  name?: string;
  date?: Date;
  content?: string;
  students?: Student[];
  report?: Report;
  topic?: Topic;
  course?: Course;
  subject?: Subject;
  tutor?: Tutor;
}

export class Exercise implements IExercise {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public date?: Date,
    public content?: string,
    public students?: Student[],
    public report?: Report,
    public topic?: Topic,
    public course?: Course,
    public subject?: Subject,
    public tutor?: Tutor) {
  }
}
