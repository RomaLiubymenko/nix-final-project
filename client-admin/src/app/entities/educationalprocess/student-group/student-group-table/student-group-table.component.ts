import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, LazyLoadEvent, MessageService} from "primeng/api";
import {IAbstractFilter} from 'src/app/shared/models/filters/abstract-filter.model';
import {AbstractTableComponent} from "../../../../shared/components/abstract-table/abstract-table.component";
import {GroupType, StudentGroup} from "../../../../shared/models/educationalprocess/student-group.model";
import {StudentGroupService} from "../../../../shared/services/educationalprocess/student-group.service";
import {TranslocoService} from "@ngneat/transloco";
import {StudentGroupFilter} from "../../../../shared/models/filters/educationalprocess/student-group-filter.model";
import {StudentService} from "../../../../shared/services/user/student.service";
import {SelectModal} from "../../../../shared/models/select.model";
import * as moment from 'moment';
import {DialogService} from "primeng/dynamicdialog";

@Component({
  selector: 'student-group-table',
  templateUrl: './student-group-table.component.html',
  styleUrls: ['./student-group-table.component.scss'],
  providers: [DialogService]
})
export class StudentGroupTableComponent extends AbstractTableComponent<StudentGroup, IAbstractFilter> implements OnInit {

  namespace = 'student-group';
  startDate: Date | undefined;
  endDate: Date | undefined;
  selectedGroupType: any;
  groupTypeForSelect: any[] = [];
  override filterEntity: StudentGroupFilter = new StudentGroupFilter();
  studentsForSelect: any[] = [];
  selectedStudentUuids: string[] = [];

  override allPropertyNames = [
    {field: 'name', selected: true},
    {field: 'description', selected: true},
    {field: 'groupType', selected: true},
    {field: 'startDate', selected: true},
    {field: 'endDate', selected: true},
    {field: 'isFormed', selected: true},
    {field: 'students', selected: true}
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public studentGroupService: StudentGroupService,
    public studentService: StudentService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public override translocoService: TranslocoService,
  ) {
    super(studentGroupService, router, activatedRoute, messageService, confirmationService, translocoService)
  }

  override ngOnInit(): void {
    this.createStudentForSelect();
    this.setGroupTypeTypeForSelect();
    this.handleNavigation();
    this.setVisibleFields();
  }

  createStudentForSelect() {
    this.studentService.getAll().subscribe(studentRes => {
      studentRes.forEach(student => this.studentsForSelect.push(new SelectModal(student.lastName + ' ' + student.firstName, student.uuid)));
    });
  }

  addStudentGroup(): void {
    this.router.navigate(['student-groups', 'new-student-group'])
  }

  editStudentGroup(studentGroup: StudentGroup): void {
    this.router.navigate(['student-groups', studentGroup.uuid, 'edit-form'])
  }

  override filter() {
    if (this.startDate) {
      this.filterEntity.startDate = moment(this.startDate).format('DD.MM.YYYY');
    }
    if (this.endDate) {
      this.filterEntity.endDate = moment(this.endDate).format('DD.MM.YYYY');
    }
    this.filterEntity.groupType = this.selectedGroupType ?? null;
    if (this.selectedStudentUuids.length) {
      this.filterEntity.studentUuids = this.selectedStudentUuids;
    }
    super.filter();
  }

  override onChangePage(data: LazyLoadEvent) {
    this.page = Math.ceil(data.first! / data.rows!) + 1;
    if (!!data.sortField && data.sortField != 'students') {
      this.predicate = data.sortField;
    } else {
      this.predicate = 'created';
    }
    this.isAscending = data.sortOrder == 1;
    if (this.itemsPerPage != data.rows!) {
      this.itemsPerPage = data.rows!;
      this.transition();
      this.loadAll();
    } else {
      this.transition();
    }
  }

  setGroupTypeTypeForSelect() {
    this.translocoService.langChanges$.subscribe(() => {
      this.groupTypeForSelect = [{
        name: this.translocoService.translate('GROUP'),
        code: GroupType.GROUP
      }, {
        name: this.translocoService.translate('MINI_GROUP'),
        code: GroupType.MINI_GROUP
      }, {
        name: this.translocoService.translate('INDIVIDUAL'),
        code: GroupType.INDIVIDUAL
      }];
    });
  }

  override clear() {
    this.selectedGroupType = undefined;
    this.startDate = undefined;
    this.endDate = undefined;
    this.selectedStudentUuids = [];
    this.filterEntity = new StudentGroupFilter();
    super.clear();
  }
}
