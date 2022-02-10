import {GenderType, IUser} from "./user.model";

export class Admin implements IUser {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public username?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: GenderType,
    public birthDay?: string,
    public activated?: boolean,
    public password?: string,
  ) {
  }
}
