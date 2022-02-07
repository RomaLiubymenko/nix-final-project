import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Tutor} from "../../models/user/tutor.model";

@Injectable({
  providedIn: 'root'
})
export class TutorService extends AbstractService<Tutor> {

  override resourceUrl = '/api/tutors';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
