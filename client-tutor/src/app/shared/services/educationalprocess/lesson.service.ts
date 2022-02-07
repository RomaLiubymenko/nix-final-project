import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Lesson} from "../../models/educationalprocess/lesson.model";

@Injectable({
  providedIn: 'root'
})
export class LessonService extends AbstractService<Lesson> {

  override resourceUrl = '/api/lessons';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
