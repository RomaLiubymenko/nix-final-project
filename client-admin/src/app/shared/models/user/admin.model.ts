import {GenderType, IUser} from "./user.model";

export class Admin implements IUser {
  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
    public username?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public gender?: GenderType,
    public birthDay?: Date,
    public activated?: boolean,
    public password?: string,
  ) {
  }
}
