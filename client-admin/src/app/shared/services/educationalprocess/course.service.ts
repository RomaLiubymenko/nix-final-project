import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Course} from '../../models/educationalprocess/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends AbstractService<Course> {

  override resourceUrl = '/api/courses';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
