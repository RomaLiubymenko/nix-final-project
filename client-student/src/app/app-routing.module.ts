import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {authorizationRoute} from "./entities/authorization/authorization.route";
import {AuthGuardService} from "./shared/services/auth/auth-guard.service";
import {navigationRoute} from "./shared/layouts/navigation/navigation.route";
import {studentRoute} from "./entities/student/student.route";

const LAYOUT_ROUTES = [navigationRoute];

const appRoutes: Routes = [
  {
    path: '',
    canActivate: [AuthGuardService],
    data: {breadcrumb: ''},
    children: [
      {path: 'students', data: {breadcrumb: 'STUDENT'}, children: studentRoute}
    ],
  },
  {path: 'authorization', data: {breadcrumb: 'AUTHORIZATION'}, children: authorizationRoute}
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        ...LAYOUT_ROUTES,
        ...appRoutes
      ]
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
