import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NavigationComponent} from './navigation.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatButtonModule} from "@angular/material/button";
import {BrowserModule} from "@angular/platform-browser";
import {BreadcrumbComponent} from "../breadcrumb/breadcrumb.component";
import {BreadcrumbModule} from "primeng/breadcrumb";
import {PanelMenuModule} from "primeng/panelmenu";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {TranslocoRootModule} from "../../../transloco-root.module";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    BreadcrumbModule,
    PanelMenuModule,
    InputTextModule,
    ButtonModule,
    DropdownModule,
    TranslocoRootModule
  ],
  declarations: [
    NavigationComponent,
    BreadcrumbComponent
  ],
  exports: [
    NavigationComponent
  ],
  providers: []
})
export class NavigationModule {
}
