import {AbstractModel} from "../abstract.model";
import {User} from "./user.model";

export interface IRole extends AbstractModel {
  name?: string;
  users?: User[];
}

export class Role implements IRole {

  constructor(
    public uuid?: string,
    public name?: string,
    public users?: User[],
  ) {
  }
}
