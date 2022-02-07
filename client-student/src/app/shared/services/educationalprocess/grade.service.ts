import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Grade} from "../../models/educationalprocess/grade.model";

@Injectable({
  providedIn: 'root'
})
export class GradeService extends AbstractService<Grade> {

  override resourceUrl = '/api/grades';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
