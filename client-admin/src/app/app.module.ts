import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {NavigationModule} from './shared/layouts/navigation/navigation.module';
import {FileUploadModule} from "primeng/fileupload";
import {CalendarModule} from "primeng/calendar";
import {SliderModule} from "primeng/slider";
import {StudentTableComponent} from "./entities/user/student/student-table/student-table.component";
import {StudentEditComponent} from "./entities/user/student/student-edit/student-edit.component";
import {
  StudentGroupTableComponent
} from "./entities/educationalprocess/student-group/student-group-table/student-group-table.component";
import {
  StudentGroupEditComponent
} from "./entities/educationalprocess/student-group/student-group-edit/student-group-edit.component";
import {ConfirmationService, MessageService} from "primeng/api";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {InputTextModule} from "primeng/inputtext";
import {CardModule} from "primeng/card";
import {RippleModule} from "primeng/ripple";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {InputNumberModule} from "primeng/inputnumber";
import {RadioButtonModule} from "primeng/radiobutton";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RatingModule} from "primeng/rating";
import {ToolbarModule} from "primeng/toolbar";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ProgressBarModule} from "primeng/progressbar";
import {ToastModule} from "primeng/toast";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {ContextMenuModule} from "primeng/contextmenu";
import {MultiSelectModule} from "primeng/multiselect";
import {DialogModule} from "primeng/dialog";
import {TableModule} from "primeng/table";
import {TranslocoRootModule} from './transloco-root.module';
import {DynamicDialogModule} from "primeng/dynamicdialog";
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {SelectButtonModule} from "primeng/selectbutton";
import {CheckboxModule} from "primeng/checkbox";
import {SignInComponent} from "./entities/authorization/sign-in/sign-in.component";
import {SignUpComponent} from "./entities/authorization/sign-up/sign-up.component";
import {AuthService} from "./shared/services/auth/auth.service";
import {AuthGuardService} from "./shared/services/auth/auth-guard.service";
import {registerLocaleData} from "@angular/common";
import localeRuUa from '@angular/common/locales/ru-UA';
import localeRu from '@angular/common/locales/ru';
import localeUa from '@angular/common/locales/uk';
import {AuthInterceptor} from "./shared/services/auth/auth-interceptor.service";
import {TranslocoService} from "@ngneat/transloco";
import {AdminFullInfoComponent} from './entities/user/admin/admin-full-info/admin-full-info.component';
import {DividerModule} from "primeng/divider";
import {PanelModule} from "primeng/panel";
import {TutorEditComponent} from './entities/user/tutor/tutor-edit/tutor-edit.component';
import {TutorTableComponent} from './entities/user/tutor/tutor-table/tutor-table.component';
import {SubjectTableComponent} from './entities/educationalprocess/subject/subject-table/subject-table.component';
import {SubjectEditComponent} from './entities/educationalprocess/subject/subject-edit/subject-edit.component';
import {AccountTableComponent} from './entities/finance/account/account-table/account-table.component';
import {AccountEditComponent} from './entities/finance/account/account-edit/account-edit.component';

const LMS_MODULES = [
  NavigationModule
];

const PRIMENG_MODULES = [
  TableModule,
  CalendarModule,
  SliderModule,
  DialogModule,
  MultiSelectModule,
  ContextMenuModule,
  DropdownModule,
  ButtonModule,
  ToastModule,
  ProgressBarModule,
  FileUploadModule,
  ToolbarModule,
  RatingModule,
  RadioButtonModule,
  InputNumberModule,
  ConfirmDialogModule,
  InputTextareaModule,
  RippleModule,
  CardModule,
  InputTextModule,
  ProgressSpinnerModule,
  DynamicDialogModule,
  SelectButtonModule,
  CheckboxModule,
  DividerModule,
  PanelModule,
];

const preLoad = {
  provide: APP_INITIALIZER,
  multi: true,
  useFactory: preloadUser,
  deps: [TranslocoService]
};

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    StudentTableComponent,
    StudentEditComponent,
    StudentGroupTableComponent,
    StudentGroupEditComponent,
    AdminFullInfoComponent,
    TutorEditComponent,
    TutorTableComponent,
    SubjectTableComponent,
    SubjectEditComponent,
    AccountTableComponent,
    AccountEditComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    ...PRIMENG_MODULES,
    ...LMS_MODULES,
    TranslocoRootModule
  ],
  providers: [
    MessageService,
    ConfirmationService,
    AuthService,
    AuthGuardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    preLoad
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function preloadUser(transloco: TranslocoService) {
  return function () {
    transloco.setActiveLang(transloco.getActiveLang());
    return transloco.load(transloco.getActiveLang()).toPromise();
  }
}

registerLocaleData(localeRuUa);
registerLocaleData(localeRu);
registerLocaleData(localeUa);
