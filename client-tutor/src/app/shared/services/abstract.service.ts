import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {createRequestOption, SearchWithPagination} from "../utils/request/param-util";
import {httpOptions} from "../utils/request/http-body-constant";
import {AbstractModel} from "../models/abstract.model";

@Injectable({
  providedIn: 'root'
})
export class AbstractService<ENTITY extends AbstractModel> {

  protected resourceUrl = '/api';

  constructor(public httpClient: HttpClient) {
  }

  save(entity: ENTITY): Observable<any> {
    if (!!entity['uuid'])
      return this.httpClient.put(`${this.resourceUrl}/${entity.uuid}`, entity, httpOptions);
    else
      return this.httpClient.post(this.resourceUrl, entity, httpOptions);
  }

  getByUuid(uuid: string): Observable<ENTITY> {
    return this.httpClient.get<ENTITY>(`${this.resourceUrl}/${uuid}`);
  }

  getAllByQuery(req?: SearchWithPagination): Observable<HttpResponse<ENTITY[]>> {
    const options = createRequestOption(req);
    return this.httpClient.get<ENTITY[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  public getAll(): Observable<ENTITY[]> {
    return this.httpClient.get<ENTITY[]>(this.resourceUrl);
  }

  delete(uuid: string): Observable<any> {
    return this.httpClient.delete(`${this.resourceUrl}/${uuid}`);
  }

  deleteEntities(elements: ENTITY[]): Observable<any> {
    let options: HttpParams = new HttpParams();
    elements.forEach(value => {
      Object.keys(value).forEach(key => {
        if (key === 'uuid') {
          options = options.append('uuid', value['uuid']!);
        }
      })
    })
    return this.httpClient.delete(`${this.resourceUrl}/uuids`, {params: options});
  }
}
