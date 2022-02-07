import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable} from "rxjs";
import {Tutor} from "../../shared/models/user/tutor.model";
import {TutorService} from "../../shared/services/user/tutor.service";
import {TutorFullInfoComponent} from "./tutor-full-info/tutor-full-info.component";

@Injectable({providedIn: 'root'})
export class TutorResolve implements Resolve<Tutor> {

  constructor(private service: TutorService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Tutor> {
    const uuid = route.params["uuid"];
    return this.service.getByUuid(uuid);
  }
}

export const tutorRoute: Routes = [
  {
    path: ':uuid/full-info',
    component: TutorFullInfoComponent,
    data: {
      breadcrumb: 'TUTOR_FULL_INFO',
    },
    resolve: {
      tutor: TutorResolve,
    }
  }
];
