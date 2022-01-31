import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable, of} from "rxjs";
import {StudentTableComponent} from "./student-table/student-table.component";
import {StudentEditComponent} from "./student-edit/student-edit.component";
import {Student} from "../../shared/models/user/student.model";
import {StudentService} from "../../shared/services/user/student.service";

@Injectable({providedIn: 'root'})
export class StudentResolve implements Resolve<Student> {

  constructor(private service: StudentService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Student> {
    const uuid = route.params["uuid"];
    if (uuid) {
      return this.service.getByUuid(uuid);
    }
    return of(new Student());
  }
}

export const studentsRoute: Routes = [
  {
    path: '',
    component: StudentTableComponent,
    data: {
      defaultSort: 'created,asc',
    },
  },
  {
    path: ':uuid/edit-form',
    component: StudentEditComponent,
    data: {
      breadcrumb: 'STUDENT',
    },
    resolve: {
      student: StudentResolve,
    }
  },
  {
    path: 'new-student',
    component: StudentEditComponent,
    data: {
      breadcrumb: 'STUDENT'
    },
    resolve: {
      student: StudentResolve,
    }
  }
];
