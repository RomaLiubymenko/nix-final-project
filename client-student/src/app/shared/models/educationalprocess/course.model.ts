import {AbstractModel} from "../abstract.model";
import {Student} from "../user/student.model";
import {Tutor} from "../user/tutor.model";
import {StudentGroup} from "./student-group.model";
import {Grade} from "./grade.model";
import {Exercise} from "./exercise.model";
import {Subject} from "./subject.model";
import {Topic} from "./topic.model";
import {Lesson} from "./lesson.model";

export interface ICourse extends AbstractModel {
  name?: string;
  description?: string;
  languageOfInstruction?: string;
  knowledgeLevelOfCourse?: string;
  startDate?: Date;
  endDate?: Date;
  isCompleted?: boolean;
  grades?: Grade[];
  exercises?: Exercise[];
  subjects?: Subject[];
  topics?: Topic[];
  lessons?: Lesson[];
  studentGroup?: StudentGroup;
  students?: Student[];
  tutors?: Tutor[];
}

export class Course implements ICourse {
  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public description?: string,
    public languageOfInstruction?: string,
    public knowledgeLevelOfCourse?: string,
    public startDate?: Date,
    public endDate?: Date,
    public isCompleted?: boolean,
    public grades?: Grade[],
    public exercises?: Exercise[],
    public subjects?: Subject[],
    public topics?: Topic[],
    public lessons?: Lesson[],
    public studentGroup?: StudentGroup,
    public students?: Student[],
    public tutors?: Tutor[]) {
  }
}
