import {AbstractModel} from "../abstract.model";
import {Course} from "./course.model";
import {Subject} from "./subject.model";
import {Lesson} from "./lesson.model";
import {Exercise} from "./exercise.model";

export interface ITopic extends AbstractModel {
  name?: string;
  description?: string;
  length?: number;
  subject?: Subject;
  course?: Course;
  lessons?: Lesson[];
  exercises?: Exercise[];
}

export class Topic implements ITopic {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public description?: string,
    public length?: number,
    public subject?: Subject,
    public course?: Course,
    public lessons?: Lesson[],
    public exercises?: Exercise[]) {
  }
}
