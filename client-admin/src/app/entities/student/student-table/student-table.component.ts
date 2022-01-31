import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractTableComponent} from "../../../shared/components/abstract-table/abstract-table.component";
import {ConfirmationService, MessageService} from "primeng/api";
import {Student} from "../../../shared/models/user/student.model";
import {StudentService} from "../../../shared/services/user/student.service";
import {DialogService} from "primeng/dynamicdialog";
import {StudentFilter} from "../../../shared/models/filters/student-filter.model";
import {GenderType} from "../../../shared/models/user/user.model";

@Component({
  selector: 'student-table',
  templateUrl: './student-table.component.html',
  styleUrls: ['./student-table.component.scss'],
  providers: [DialogService]
})
export class StudentTableComponent extends AbstractTableComponent<Student, StudentFilter> implements OnInit {

  genderTypeForSelect: any[] = [];
  searchDate: Date;
  selectedGender: any;
  override filterEntity: StudentFilter = new StudentFilter();

  override allPropertyNames = [
    {field: 'firstName', header: 'First Name', selected: true},
    {field: 'lastName', header: 'Last Name', selected: true},
    {field: 'email', header: 'Email', selected: true},
    {field: 'gender', header: 'Gender', selected: true},
    {field: 'birthDay', header: 'BirthDay', selected: true},
    {field: 'activated', header: 'Active', selected: true},
    {field: 'studentGroups', header: 'Student Groups', selected: true},
  ];

  constructor(
    public override router: Router,
    public override activatedRoute: ActivatedRoute,
    public studentService: StudentService,
    public override messageService: MessageService,
    public override confirmationService: ConfirmationService,
    public dialogService: DialogService
  ) {
    super(studentService, router, activatedRoute, messageService, confirmationService);
    this.genderTypeForSelect.push({
      name: 'MALE',
      code: GenderType.MALE
    });
    this.genderTypeForSelect.push({
      name: 'FEMALE',
      code: GenderType.FEMALE
    });
  }

  addStudent(): void {
    this.router.navigate(['students', 'new-student'])
  }

  editStudent(student: Student): void {
    this.router.navigate(['students', student.uuid, 'edit-form'])
  }

  override filter() {
    this.filterEntity.gender = this.selectedGender === undefined ? null : this.selectedGender.code;
    this.filterEntity.birthDay = this.searchDate?.toLocaleDateString()
    super.filter();
  }

  asd($event: any) {
    console.log($event)
  }
}
