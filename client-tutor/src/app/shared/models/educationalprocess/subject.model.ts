import {AbstractModel} from "../abstract.model";
import {Tutor} from "../user/tutor.model";
import {Course} from "./course.model";
import {Lesson} from "./lesson.model";
import {Topic} from "./topic.model";
import {Exercise} from "./exercise.model";

export interface ISubject extends AbstractModel {
  name?: string;
  description?: string;
  courses?: Course[];
  lessons?: Lesson[];
  topics?: Topic[];
  exercises?: Exercise[];
  tutor?: Tutor;
}

export class Subject implements ISubject {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public description?: string,
    public courses?: Course[],
    public lessons?: Lesson[],
    public topics?: Topic[],
    public exercises?: Exercise[],
    public tutor?: Tutor) {
  }
}
