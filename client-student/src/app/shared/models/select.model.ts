export interface ISelectModal {
  name: string,
  code: any
}

export class SelectModal implements ISelectModal {
  constructor(
    public name: string,
    public code: any) {
  }
}
