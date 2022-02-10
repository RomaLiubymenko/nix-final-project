import {Component, OnInit} from '@angular/core';
import {AbstractTableComponent} from "../../../../shared/components/abstract-table/abstract-table.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslocoService} from "@ngneat/transloco";
import {ConfirmationService, LazyLoadEvent, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {SelectModal} from "../../../../shared/models/select.model";
import * as moment from "moment";
import {GenderType} from "../../../../shared/models/user/user.model";
import {Tutor, TutorStatus} from "../../../../shared/models/user/tutor.model";
import {TutorFilter} from "../../../../shared/models/filters/user/tutor-filter.model";
import {SubjectService} from "../../../../shared/services/educationalprocess/subject.service";
import {TutorService} from "../../../../shared/services/user/tutor.service";

@Component({
  selector: 'tutor-table',
  templateUrl: './tutor-table.component.html',
  styleUrls: ['./tutor-table.component.scss'],
  providers: [DialogService]
})
export class TutorTableComponent extends AbstractTableComponent<Tutor, TutorFilter> implements OnInit {

  namespace = 'tutor';
  genderTypeForSelect: any[] = [];
  statusForSelect: any[] = [];
  searchDate: Date | undefined;
  selectedGender: any;
  selectedStatus: any;
  override filterEntity: TutorFilter = new TutorFilter();
  subjectForSelect: any[] = [];
  selectedSubjectUuids: string[] = [];

  override allPropertyNames = [
    {field: 'firstName', selected: true},
    {field: 'lastName', selected: true},
    {field: 'email', selected: true},
    {field: 'gender', selected: true},
    {field: 'birthDay', selected: true},
    {field: 'status', selected: true},
    {field: 'activated', selected: true},
    {field: 'subjects', selected: true},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public tutorService: TutorService,
    public subjectService: SubjectService,
    public override translocoService: TranslocoService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public dialogService: DialogService) {
    super(tutorService, router, activatedRoute, messageService, confirmationService, translocoService);
  }

  override ngOnInit(): void {
    this.createSubjectForSelect();
    this.setGenderTypeForSelect();
    this.setStatusTypeForSelect();
    this.handleNavigation();
    this.setVisibleFields();
  }

  createSubjectForSelect() {
    this.subjectService.getAll().subscribe(subjectRes => {
      subjectRes.forEach(subject => this.subjectForSelect.push(new SelectModal(subject.name!, subject.uuid)))
    });
  }

  addTutor(): void {
    this.router.navigate(['tutors', 'new-tutor'])
  }

  editTutor(tutor: Tutor): void {
    this.router.navigate(['tutors', tutor.uuid, 'edit-form'])
  }

  override filter() {
    this.filterEntity.gender = this.selectedGender ?? null;
    this.filterEntity.status = this.selectedStatus ?? null;
    if (this.searchDate) {
      this.filterEntity.birthDay = moment(this.searchDate).format('DD.MM.YYYY');
    }
    if (this.selectedSubjectUuids.length) {
      this.filterEntity.subjectUuids = this.selectedSubjectUuids;
    }
    super.filter();
  }

  override onChangePage(data: LazyLoadEvent) {
    this.page = Math.ceil(data.first! / data.rows!) + 1;
    if (!!data.sortField && data.sortField != 'subjects') {
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

  setStatusTypeForSelect() {
    this.translocoService.langChanges$.subscribe(() => {
      this.statusForSelect = [{
        name: this.translocoService.translate('WORKING'),
        code: TutorStatus.WORKING
      }, {
        name: this.translocoService.translate('SICK'),
        code: TutorStatus.SICK
      }, {
        name: this.translocoService.translate('ON_VACATION'),
        code: TutorStatus.ON_VACATION
      }, {
        name: this.translocoService.translate('ON_UNSCHEDULED_VACATION'),
        code: TutorStatus.ON_UNSCHEDULED_VACATION
      }, {
        name: this.translocoService.translate('DAY_OFF'),
        code: TutorStatus.DAY_OFF
      }, {
        name: this.translocoService.translate('FIRED'),
        code: TutorStatus.FIRED
      }];
    });
  }

  override clear() {
    this.selectedGender = undefined;
    this.selectedStatus = undefined;
    this.searchDate = undefined;
    this.selectedSubjectUuids = [];
    this.filterEntity = new TutorFilter();
    super.clear();
  }
}
