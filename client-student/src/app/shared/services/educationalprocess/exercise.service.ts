import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Exercise} from "../../models/educationalprocess/exercise.model";

@Injectable({
  providedIn: 'root'
})
export class ExerciseService extends AbstractService<Exercise> {

  override resourceUrl = '/api/exercises';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
