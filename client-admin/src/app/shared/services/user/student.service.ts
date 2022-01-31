import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "../../models/user/student.model";
import {AbstractService} from "../abstract.service";

@Injectable({
  providedIn: 'root'
})
export class StudentService extends AbstractService<Student> {

  override resourceUrl = '/api/students';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
