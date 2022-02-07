import {IAbstractFilter} from "../abstract-filter.model";

export class RoomFilter implements IAbstractFilter {

  constructor(
    public name?: string,
    public number?: string,
    public capacity?: string,
    public isAvailabilityToUse?: string,
    public description?: string,
  ) {
  }
}
