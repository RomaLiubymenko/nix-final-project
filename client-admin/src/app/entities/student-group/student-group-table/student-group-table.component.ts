import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {IAbstractFilter} from 'src/app/shared/models/filters/abstract-filter.model';
import {AbstractTableComponent} from "../../../shared/components/abstract-table/abstract-table.component";
import {StudentGroup} from "../../../shared/models/educationalprocess/student-group.model";
import {StudentGroupService} from "../../../shared/services/educationalprocess/student-group.service";
import {TranslocoService} from "@ngneat/transloco";

@Component({
  selector: 'student-group-table',
  templateUrl: './student-group-table.component.html',
  styleUrls: ['./student-group-table.component.scss']
})
export class StudentGroupTableComponent extends AbstractTableComponent<StudentGroup, IAbstractFilter> implements OnInit {

  override allPropertyNames = [
    {field: 'name', header: 'Group name', selected: true},
    {field: 'description', header: 'Description', selected: true},
    {field: 'groupType', header: 'Group type', selected: true},
    {field: 'students', header: 'Students', selected: true},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public studentGroupService: StudentGroupService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public override translocoService: TranslocoService,
  ) {
    super(studentGroupService, router, activatedRoute, messageService, confirmationService, translocoService)
  }

  addStudentGroup(): void {
    this.router.navigate(['student-groups', 'new-student-group'])
  }

  editStudentGroup(studentGroup: StudentGroup): void {
    this.router.navigate(['student-groups', studentGroup.uuid, 'edit-form'])
  }
}
