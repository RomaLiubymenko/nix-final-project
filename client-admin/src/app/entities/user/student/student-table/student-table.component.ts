import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractTableComponent} from "../../../../shared/components/abstract-table/abstract-table.component";
import {ConfirmationService, LazyLoadEvent, MessageService} from "primeng/api";
import {Student} from "../../../../shared/models/user/student.model";
import {StudentService} from "../../../../shared/services/user/student.service";
import {DialogService} from "primeng/dynamicdialog";
import {StudentFilter} from "../../../../shared/models/filters/user/student-filter.model";
import {GenderType} from "../../../../shared/models/user/user.model";
import {TranslocoService} from "@ngneat/transloco";
import {SelectModal} from "../../../../shared/models/select.model";
import {StudentGroupService} from "../../../../shared/services/educationalprocess/student-group.service";
import * as moment from "moment";

@Component({
  selector: 'student-table',
  templateUrl: './student-table.component.html',
  styleUrls: ['./student-table.component.scss'],
  providers: [DialogService]
})
export class StudentTableComponent extends AbstractTableComponent<Student, StudentFilter> implements OnInit {

  namespace = 'student';
  genderTypeForSelect: any[] = [];
  searchDate: Date | undefined;
  selectedGender: any;
  override filterEntity: StudentFilter = new StudentFilter();
  groupsForSelect: any[] = [];
  selectedGroupUuids: string[] = [];

  override allPropertyNames = [
    {field: 'firstName', selected: true},
    {field: 'lastName', selected: true},
    {field: 'email', selected: true},
    {field: 'gender', selected: true},
    {field: 'birthDay', selected: true},
    {field: 'activated', selected: true},
    {field: 'studentGroups', selected: true},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public studentService: StudentService,
    public studentGroupService: StudentGroupService,
    public override translocoService: TranslocoService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public dialogService: DialogService) {
    super(studentService, router, activatedRoute, messageService, confirmationService, translocoService);
  }

  override ngOnInit(): void {
    this.createGroupForSelect();
    this.setGenderTypeForSelect();
    this.handleNavigation();
    this.setVisibleFields();
  }

  createGroupForSelect() {
    this.studentGroupService.getAll().subscribe(groupRes => {
      groupRes.forEach(group => this.groupsForSelect.push(new SelectModal(group.name!, group.uuid)))
    });
  }

  addStudent(): void {
    this.router.navigate(['students', 'new-student'])
  }

  editStudent(student: Student): void {
    this.router.navigate(['students', student.uuid, 'edit-form'])
  }

  override filter() {
    this.filterEntity.gender = this.selectedGender ?? null;
    if (this.searchDate) {
      this.filterEntity.birthDay = moment(this.searchDate).format('DD.MM.YYYY');
    }
    if (this.selectedGroupUuids.length) {
      this.filterEntity.studentGroupUuids = this.selectedGroupUuids;
    }
    super.filter();
  }

  override onChangePage(data: LazyLoadEvent) {
    this.page = Math.ceil(data.first! / data.rows!) + 1;
    if (!!data.sortField && data.sortField != 'studentGroups') {
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

  setGenderTypeForSelect() {
    this.translocoService.langChanges$.subscribe(() => {
      this.genderTypeForSelect = [{
        name: this.translocoService.translate('MALE'),
        code: GenderType.MALE
      }, {
        name: this.translocoService.translate('FEMALE'),
        code: GenderType.FEMALE
      }];
    });
  }

  override clear() {
    this.selectedGender = undefined;
    this.searchDate = undefined;
    this.selectedGroupUuids = [];
    this.filterEntity = new StudentFilter();
    super.clear();
  }
}
