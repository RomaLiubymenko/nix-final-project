import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Topic} from "../../models/educationalprocess/topic.model";

@Injectable({
  providedIn: 'root'
})
export class TopicService extends AbstractService<Topic> {

  override resourceUrl = '/api/topics';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
