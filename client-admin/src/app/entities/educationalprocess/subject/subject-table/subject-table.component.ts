import {Component, OnInit} from '@angular/core';
import {AbstractTableComponent} from "../../../../shared/components/abstract-table/abstract-table.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TranslocoService} from "@ngneat/transloco";
import {ConfirmationService, LazyLoadEvent, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {SelectModal} from "../../../../shared/models/select.model";
import {Subject} from "../../../../shared/models/educationalprocess/subject.model";
import {SubjectFilter} from "../../../../shared/models/filters/educationalprocess/subject-filter.model";
import {SubjectService} from "../../../../shared/services/educationalprocess/subject.service";
import {TutorService} from "../../../../shared/services/user/tutor.service";

@Component({
  selector: 'subject-table',
  templateUrl: './subject-table.component.html',
  styleUrls: ['./subject-table.component.scss'],
  providers: [DialogService]
})
export class SubjectTableComponent extends AbstractTableComponent<Subject, SubjectFilter> implements OnInit {

  namespace = 'subject';
  override filterEntity: SubjectFilter = new SubjectFilter();
  tutors: any[] = [];
  selectedTutorUuid: string | undefined;

  override allPropertyNames = [
    {field: 'name', selected: true},
    {field: 'description', selected: true},
    {field: 'tutor', selected: true}
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public subjectService: SubjectService,
    public tutorService: TutorService,
    public override translocoService: TranslocoService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public dialogService: DialogService) {
    super(subjectService, router, activatedRoute, messageService, confirmationService, translocoService);
  }

  override ngOnInit(): void {
    this.createTutorForSelect();
    this.handleNavigation();
    this.setVisibleFields();
  }

  createTutorForSelect() {
    this.tutorService.getAll().subscribe(tutorRes => {
      tutorRes.forEach(tutor => this.tutors.push(new SelectModal(tutor.lastName + ' ' + tutor.firstName, tutor.uuid)));
    });
  }

  addSubject(): void {
    this.router.navigate(['subjects', 'new-subject']);
  }

  editSubject(subject: Subject): void {
    this.router.navigate(['subjects', subject.uuid, 'edit-form'])
  }

  override filter() {
    if (this.selectedTutorUuid) {
      this.filterEntity.tutorUuid = this.selectedTutorUuid;
    }
    super.filter();
  }

  override onChangePage(data: LazyLoadEvent) {
    this.page = Math.ceil(data.first! / data.rows!) + 1;
    if (!!data.sortField && data.sortField != 'tutor') {
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

  override clear() {
    this.filterEntity = new SubjectFilter();
    this.selectedTutorUuid = undefined;
    super.clear();
  }
}
