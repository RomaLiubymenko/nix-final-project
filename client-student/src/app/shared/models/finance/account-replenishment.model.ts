import {AbstractModel} from "../abstract.model";
import {Student} from "../user/student.model";


export interface IAccountReplenishment extends AbstractModel {
  date?: Date;
  amount?: number;
  payer?: string;
  purpose?: string;
  comment?: string;
  replenishmentMethod?: ReplenishmentMethod;
  student?: Student;
}

export class AccountReplenishment implements IAccountReplenishment {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public date?: Date,
    public amount?: number,
    public payer?: string,
    public purpose?: string,
    public comment?: string,
    public replenishmentMethod?: ReplenishmentMethod,
    public student?: Student) {
  }
}

export enum ReplenishmentMethod {
  CASH = "CASH",
  CASHLESS_PAYMENT = "CASHLESS_PAYMENT"
}
