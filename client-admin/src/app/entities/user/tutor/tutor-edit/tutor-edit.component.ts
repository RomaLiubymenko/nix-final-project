import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {TranslocoService} from "@ngneat/transloco";
import * as moment from "moment";
import {SelectModal} from "../../../../shared/models/select.model";
import {GenderType} from "../../../../shared/models/user/user.model";
import {Tutor, TutorStatus} from "../../../../shared/models/user/tutor.model";
import {Subject} from "../../../../shared/models/educationalprocess/subject.model";
import {TutorService} from "../../../../shared/services/user/tutor.service";
import {SubjectService} from "../../../../shared/services/educationalprocess/subject.service";

@Component({
  selector: 'tutor-edit',
  templateUrl: './tutor-edit.component.html',
  styleUrls: ['./tutor-edit.component.scss']
})
export class TutorEditComponent implements OnInit {

  tutor: Tutor;
  selectedSubjectUuids: string[] = [];
  subjectsForSelect: any[] = [];
  subjects: Subject[] = [];
  genderTypeForSelect: any[] = [];
  statusForSelect: any[] = [];
  birthDay: Date;
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tutorService: TutorService,
    private subjectService: SubjectService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private translocoService: TranslocoService) {
  }

  ngOnInit(): void {
    this.setGenderTypeForSelect();
    this.setStatusTypeForSelect();
    this.route.data.subscribe(({tutor}) => {
      this.tutor = tutor;
      if (this.tutor.birthDay) {
        this.birthDay = new Date(this.tutor.birthDay);
      }
      this.setSelectedSubject();
      this.createSubjectForSelect();
    });
  }

  save() {
    this.confirmationService.confirm({
      message: this.translocoService.translate('CONFIRMATION_TO_SAVE_ENTITY'),
      header: this.translocoService.translate('CONFIRMATION'),
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.preparedSubjectForSave();
        if (this.birthDay) {
          this.tutor.birthDay = moment(this.birthDay).format('YYYY-MM-DD');
        }
        this.tutorService.save(this.tutor).subscribe({
          next: () => this.router.navigate(['tutors']),
          error: err => {
            this.messageService.add({
              severity: 'error',
              summary: this.translocoService.translate('ERROR'),
              detail: err.error.error.join('\n'),
              life: 20000
            });
          }
        });
      },
      reject: () => {
        this.confirmationService.close();
      }
    });
  }

  setSelectedSubject() {
    this.selectedSubjectUuids = this.tutor.subjects!?.map(subject => subject.uuid!);
  }

  createSubjectForSelect() {
    this.subjectService.getAll().subscribe(subjectRes => {
      this.subjects = subjectRes.map(subject => {
        this.subjectsForSelect.push(new SelectModal(subject.name!, subject.uuid));
        return subject;
      });
      this.isLoading = false;
    });
  }

  preparedSubjectForSave() {
    if (this.selectedSubjectUuids.length) {
      this.tutor.subjects = this.subjects.filter(subject => this.selectedSubjectUuids.includes(subject.uuid!));
    } else {
      this.tutor.subjects = [];
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
}
