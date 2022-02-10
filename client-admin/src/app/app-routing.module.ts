import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {navigationRoute} from "./shared/layouts/navigation/navigation.route";
import {studentGroupsRoute} from "./entities/educationalprocess/student-group/student-group.route";
import {studentsRoute} from "./entities/user/student/student.route";
import {AuthGuardService} from "./shared/services/auth/auth-guard.service";
import {authorizationRoute} from "./entities/authorization/authorization.route";
import {adminRoute} from "./entities/user/admin/admin.route";
import {tutorRoutes} from "./entities/user/tutor/tutor.route";
import {subjectRoutes} from "./entities/educationalprocess/subject/subject.route";
import {accountRoute} from "./entities/finance/account/account.route";

const LAYOUT_ROUTES = [navigationRoute];

const appRoutes: Routes = [
  {
    path: '',
    canActivate: [AuthGuardService],
    data: {breadcrumb: ''},
    children: [
      {path: 'admins', data: {breadcrumb: 'ADMIN'}, children: adminRoute},
      {path: 'students', data: {breadcrumb: 'STUDENTS'}, children: studentsRoute},
      {path: 'student-groups', data: {breadcrumb: 'STUDENT_GROUPS'}, children: studentGroupsRoute},
      {path: 'tutors', data: {breadcrumb: 'TUTORS'}, children: tutorRoutes},
      {path: 'subjects', data: {breadcrumb: 'SUBJECTS'}, children: subjectRoutes},
      {path: 'accounts', data: {breadcrumb: 'ACCOUNTS'}, children: accountRoute},
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
