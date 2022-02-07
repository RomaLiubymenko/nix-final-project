import {AbstractModel} from "../abstract.model";
import {Course} from "./course.model";
import {Subject} from "./subject.model";
import {Attendance} from "../finance/attendance.model";
import {Student} from "../user/student.model";
import {Room} from "../location/room.model";
import {Topic} from "./topic.model";
import {Tutor} from "../user/tutor.model";
import {StudentGroup} from "./student-group.model";

export interface ILesson extends AbstractModel {
  name?: string;
  description?: string;
  date?: Date;
  length?: number;
  lessonType?: LessonType;
  lessonStatus?: LessonStatus;
  attendances?: Attendance[];
  students?: Student[];
  subject?: Subject;
  topics?: Topic[];
  course?: Course;
  room?: Room;
  tutors?: Tutor[]
  studentGroups?: StudentGroup[];
}

export class Lesson implements ILesson {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public description?: string,
    public date?: Date,
    public length?: number,
    public lessonType?: LessonType,
    public lessonStatus?: LessonStatus,
    public attendances?: Attendance[],
    public students?: Student[],
    public subject?: Subject,
    public topics?: Topic[],
    public course?: Course,
    public room?: Room,
    public tutors?: Tutor[],
    public studentGroups?: StudentGroup[]) {
  }
}

export enum LessonType {
  LESSON = 'LESSON',
  OPEN_LESSON = 'OPEN_LESSON',
  ONLINE_LESSON = 'ONLINE_LESSON',
  TRIAL_LESSON = 'TRIAL_LESSON',
}

export enum LessonStatus {
  NEW = 'NEW',
  COMPLETED = 'COMPLETED',
  CANCELLED = 'CANCELLED'
}
