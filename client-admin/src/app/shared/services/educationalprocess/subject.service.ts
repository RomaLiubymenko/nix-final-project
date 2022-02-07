import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Subject} from "../../models/educationalprocess/subject.model";

@Injectable({
  providedIn: 'root'
})
export class SubjectService extends AbstractService<Subject> {

  override resourceUrl = '/api/subjects';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
