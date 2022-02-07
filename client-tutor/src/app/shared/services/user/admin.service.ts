import {Injectable} from '@angular/core';
import {AbstractService} from "../abstract.service";
import {HttpClient} from "@angular/common/http";
import {Admin} from "../../models/user/admin.model";

@Injectable({
  providedIn: 'root'
})
export class AdminService extends AbstractService<Admin> {

  override resourceUrl = '/api/admins';

  constructor(public override httpClient: HttpClient) {
    super(httpClient);
  }
}
