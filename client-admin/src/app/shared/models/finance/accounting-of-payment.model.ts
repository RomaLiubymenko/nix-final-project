import {AbstractModel} from "../abstract.model";
import {Tutor} from "../user/tutor.model";

export interface IAccountingOfPayment extends AbstractModel {
  date?: Date;
  amount?: number,
  purpose?: string;
  comment?: string;
  tutor?: Tutor;
}

export class AccountingOfPayment implements IAccountingOfPayment {

  constructor(
    public uuid?: string,
    public created?: Date,
    public updated?: Date,
    public date?: Date,
    public amount?: number,
    public purpose?: string,
    public comment?: string,
    public tutor?: Tutor
  ) {
  }
}
