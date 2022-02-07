import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Report} from "../../models/educationalprocess/report.model";

@Injectable({
  providedIn: 'root'
})
export class ReportService extends AbstractService<Report> {

  override resourceUrl = '/api/reports';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
