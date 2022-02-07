import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Attendance} from "../../models/finance/attendance.model";

@Injectable({
  providedIn: 'root'
})
export class AttendanceService extends AbstractService<Attendance> {

  override resourceUrl = '/api/attendances';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
