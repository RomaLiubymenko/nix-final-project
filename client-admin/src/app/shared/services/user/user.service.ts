import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../models/user/user.model";
import {HttpClient} from "@angular/common/http";
import {AbstractService} from "../abstract.service";

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<User> {

  public override resourceUrl = '/api/users';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }

  public getUserByUsername(username: string): Observable<User> {
    return this.httpClient.get<User>(`${this.resourceUrl}/user/${username}`);
  }
}
