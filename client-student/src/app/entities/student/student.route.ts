import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable} from "rxjs";
import {Student} from "../../shared/models/user/student.model";
import {StudentService} from "../../shared/services/user/student.service";
import {StudentFullInfoComponent} from "./student-full-info/student-full-info.component";

@Injectable({providedIn: 'root'})
export class StudentResolve implements Resolve<Student> {

  constructor(private service: StudentService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Student> {
    const uuid = route.params["uuid"];
    return this.service.getByUuid(uuid);
  }
}

export const studentRoute: Routes = [
  {
    path: ':uuid/full-info',
    component: StudentFullInfoComponent,
    data: {
      breadcrumb: 'STUDENT_FULL_INFO',
    },
    resolve: {
      student: StudentResolve,
    }
  }
];
