import {AbstractModel} from "../abstract.model";
import {Lesson} from "../educationalprocess/lesson.model";

export interface IRoom extends AbstractModel {
  name?: string;
  number?: string;
  capacity?: number;
  isAvailabilityToUse?: boolean;
  description?: string;
  lessons?: Lesson[];
}

export class Room implements IRoom {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public name?: string,
    public number?: string,
    public capacity?: number,
    public isAvailabilityToUse?: boolean,
    public description?: string,
    public lessons?: Lesson[]) {
  }
}

