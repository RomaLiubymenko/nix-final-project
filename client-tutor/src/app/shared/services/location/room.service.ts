import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Room} from "../../models/location/room.model";

@Injectable({
  providedIn: 'root'
})
export class RoomService extends AbstractService<Room> {

  override resourceUrl = '/api/rooms';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
