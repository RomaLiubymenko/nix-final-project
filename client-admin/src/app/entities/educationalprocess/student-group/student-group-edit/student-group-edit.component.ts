import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ConfirmationService, MessageService} from "primeng/api";
import {Student} from "../../../../shared/models/user/student.model";
import {StudentService} from "../../../../shared/services/user/student.service";
import {TranslocoService} from "@ngneat/transloco";
import * as moment from "moment";
import {SelectModal} from "../../../../shared/models/select.model";
import {GroupType, StudentGroup} from "../../../../shared/models/educationalprocess/student-group.model";
import {StudentGroupService} from "../../../../shared/services/educationalprocess/student-group.service";

@Component({
  selector: 'student-group-edit',
  templateUrl: './student-group-edit.component.html',
  styleUrls: ['./student-group-edit.component.scss']
})
export class StudentGroupEditComponent implements OnInit {

  group: StudentGroup;
  startDate: Date;
  endDate: Date;
  students: Student[] = [];
  selectedStudentUuids: string[] = []
  studentsForSelect: any[] = [];
  groupTypeForSelect: any[] = [];
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentGroupService: StudentGroupService,
    private studentService: StudentService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private translocoService: TranslocoService) {
  }

  ngOnInit(): void {
    this.setGroupTypeTypeForSelect();
    this.route.data.subscribe(({group}) => {
      this.group = group;
      if (this.group.startDate) {
        this.startDate = new Date(this.group.startDate);
      }
      if (this.group.endDate) {
        this.endDate = new Date(this.group.endDate);
      }
      this.setSelectedStudent();
      this.createStudentForSelect();
    });
  }

  save() {
    this.confirmationService.confirm({
      message: this.translocoService.translate('CONFIRMATION_TO_SAVE_ENTITY'),
      header: this.translocoService.translate('CONFIRMATION'),
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.preparedStudentForSave();
        if (this.startDate) {
          this.group.startDate = moment(this.startDate).format('YYYY-MM-DD');
        }
        if (this.endDate) {
          this.group.endDate = moment(this.endDate).format('YYYY-MM-DD');
        }
        this.studentGroupService.save(this.group).subscribe({
          next: () => this.router.navigate(['student-groups']),
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

  setSelectedStudent() {
    this.selectedStudentUuids = this.group.students!?.map(student => student.uuid!);
  }

  createStudentForSelect() {
    this.studentService.getAll().subscribe(studentRes => {
      this.students = studentRes.map(student => {
        this.studentsForSelect.push(new SelectModal(student.lastName + ' ' + student.firstName, student.uuid));
        return student;
      })
      this.isLoading = false;
    });
  }

  preparedStudentForSave() {
    if (this.selectedStudentUuids.length) {
      this.group.students = this.students.filter(student => this.selectedStudentUuids.includes(student.uuid!));
    } else {
      this.group.students = [];
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
}
