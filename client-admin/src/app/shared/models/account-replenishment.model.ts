import {AbstractModel} from "./abstract.model";
import {Student} from "./user/student.model";


export interface IAccountReplenishment extends AbstractModel {
  date?: Date;
  amount?: string;
  payer?: string;
  purpose?: string;
  comment?: string;
  student?: Student;
  replenishmentMethod?: ReplenishmentMethod;
}

export class AccountReplenishment implements IAccountReplenishment {

  constructor(
    public uuid?: string,
    public created?: string,
    public updated?: string,
    public date?: Date,
    public amount?: string,
    public payer?: string,
    public purpose?: string,
    public comment?: string,
    public student?: Student,
    public replenishmentMethod?: ReplenishmentMethod
  ) {
  }
}

export enum ReplenishmentMethod {
  CASH = "CASH", CASHLESS_PAYMENT = "CASHLESS_PAYMENT"
}
