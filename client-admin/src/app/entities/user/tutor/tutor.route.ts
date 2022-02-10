import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, Routes} from "@angular/router";
import {Observable, of} from "rxjs";
import {Tutor} from "../../../shared/models/user/tutor.model";
import {TutorService} from "../../../shared/services/user/tutor.service";
import {TutorTableComponent} from "./tutor-table/tutor-table.component";
import {TutorEditComponent} from "./tutor-edit/tutor-edit.component";

@Injectable({providedIn: 'root'})
export class TutorResolve implements Resolve<Tutor> {

  constructor(private service: TutorService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Tutor> {
    const uuid = route.params["uuid"];
    if (uuid) {
      return this.service.getByUuid(uuid);
    }
    return of(new Tutor());
  }
}

export const tutorRoutes: Routes = [
  {
    path: '',
    component: TutorTableComponent,
    data: {
      defaultSort: 'created,asc',
    },
  },
  {
    path: ':uuid/edit-form',
    component: TutorEditComponent,
    data: {
      breadcrumb: 'TUTOR',
    },
    resolve: {
      tutor: TutorResolve,
    }
  },
  {
    path: 'new-tutor',
    component: TutorEditComponent,
    data: {
      breadcrumb: 'TUTOR'
    },
    resolve: {
      tutor: TutorResolve,
    }
  }
];
