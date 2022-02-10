import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable, of} from "rxjs";
import {Subject} from "../../../shared/models/educationalprocess/subject.model";
import {SubjectService} from "../../../shared/services/educationalprocess/subject.service";
import {SubjectTableComponent} from "./subject-table/subject-table.component";
import {SubjectEditComponent} from "./subject-edit/subject-edit.component";

@Injectable({providedIn: 'root'})
export class SubjectResolve implements Resolve<Subject> {

  constructor(private service: SubjectService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Subject> {
    const uuid = route.params["uuid"];
    if (uuid) {
      return this.service.getByUuid(uuid);
    }
    return of(new Subject());
  }
}

export const subjectRoutes: Routes = [
  {
    path: '',
    component: SubjectTableComponent,
    data: {
      defaultSort: 'created,asc',
    },
  },
  {
    path: ':uuid/edit-form',
    component: SubjectEditComponent,
    data: {
      breadcrumb: 'SUBJECT',
    },
    resolve: {
      subject: SubjectResolve,
    }
  },
  {
    path: 'new-subject',
    component: SubjectEditComponent,
    data: {
      breadcrumb: 'SUBJECT'
    },
    resolve: {
      subject: SubjectResolve,
    }
  }
];
