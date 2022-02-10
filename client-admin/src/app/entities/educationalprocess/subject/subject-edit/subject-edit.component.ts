import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {TranslocoService} from "@ngneat/transloco";
import {SelectModal} from "../../../../shared/models/select.model";
import {Subject} from "../../../../shared/models/educationalprocess/subject.model";
import {TutorService} from "../../../../shared/services/user/tutor.service";
import {SubjectService} from "../../../../shared/services/educationalprocess/subject.service";
import {Tutor} from "../../../../shared/models/user/tutor.model";

@Component({
  selector: 'subject-edit',
  templateUrl: './subject-edit.component.html',
  styleUrls: ['./subject-edit.component.scss']
})
export class SubjectEditComponent implements OnInit {

  subject: Subject;
  selectedTutorUuid: string;
  tutorsForSelect: any[] = [];
  tutors: Tutor[] = [];
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private subjectService: SubjectService,
    private tutorService: TutorService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private translocoService: TranslocoService) {
  }

  ngOnInit(): void {
    this.route.data.subscribe(({subject}) => {
      this.subject = subject;
      this.setSelectedTutor();
      this.createTutorForSelect();
    });
  }

  save() {
    this.confirmationService.confirm({
      message: this.translocoService.translate('CONFIRMATION_TO_SAVE_ENTITY'),
      header: this.translocoService.translate('CONFIRMATION'),
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.preparedTutorForSave();
        this.subjectService.save(this.subject).subscribe({
          next: () => this.router.navigate(['subjects']),
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

  setSelectedTutor() {
    if (this.subject.tutor) {
      this.selectedTutorUuid = this.subject.tutor.uuid!;
    }
  }

  createTutorForSelect() {
    this.tutorService.getAll().subscribe(tutorsRes => {
      this.tutors = tutorsRes.map(tutor => {
        this.tutorsForSelect.push(new SelectModal(tutor.lastName + ' ' + tutor.firstName, tutor.uuid));
        return tutor;
      })
      this.isLoading = false;
    });
  }

  preparedTutorForSave() {
    if (this.selectedTutorUuid) {
      const tutorIndex = this.tutors.map(tutor => tutor.uuid).indexOf(this.selectedTutorUuid)
      this.subject.tutor = this.tutors[tutorIndex]
    }
  }
}
