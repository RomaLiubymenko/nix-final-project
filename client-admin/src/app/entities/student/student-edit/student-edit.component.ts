import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentGroupService} from "../../../shared/services/educationalprocess/student-group.service";
import {StudentGroup} from 'src/app/shared/models/educationalprocess/student-group.model';
import {ConfirmationService, MessageService} from "primeng/api";
import {Student} from "../../../shared/models/user/student.model";
import {StudentService} from "../../../shared/services/user/student.service";
import {SelectModal} from "../../../shared/models/select.model";
import {TranslocoService} from "@ngneat/transloco";
import {GenderType} from "../../../shared/models/user/user.model";

@Component({
  selector: 'student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.scss']
})
export class StudentEditComponent implements OnInit {

  student: Student;
  selectedGroupUuids: string[] = [];
  groupsForSelect: any[] = [];
  groups: StudentGroup[] = [];
  genderTypeForSelect: any[] = [];
  isLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService,
    private studentGroupService: StudentGroupService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private translocoService: TranslocoService
  ) {
  }

  ngOnInit(): void {
    this.setGenderTypeForSelect();
    this.route.data.subscribe(({student}) => {
      this.student = student;
      this.setSelectedGroup();
      this.createGroupForSelect();
    });
  }

  save() {
    this.translocoService.langChanges$.subscribe(() => {
      this.confirmationService.confirm({
        message: this.translocoService.translate('CONFIRMATION_TO_SAVE_ENTITY'),
        header: 'Confirm',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          this.preparedGroupForSave();
          console.log(this.student)
          this.studentService.save(this.student).subscribe({
            next: () => this.router.navigate(['students']),
            error: err => {
              this.messageService.add({
                severity: 'error',
                summary: 'Error',
                detail: err.error.error.join('\n'),
                life: 20000
              });
            }
          });
        }
      });
    })
  }

  setSelectedGroup() {
    this.selectedGroupUuids = this.student.studentGroups!?.map(group => group.uuid!);
  }

  createGroupForSelect() {
    this.studentGroupService.getAll().subscribe(groupRes => {
      this.groups = groupRes.map(group => {
        this.groupsForSelect.push(new SelectModal(group.name!, group.uuid));
        return group;
      })
      this.isLoading = false;
    });
  }

  preparedGroupForSave() {
    if (this.selectedGroupUuids.length) {
      this.student.studentGroups = this.groups.filter(group => this.selectedGroupUuids.includes(group.uuid!));
    } else {
      this.student.studentGroups = [];
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
}

