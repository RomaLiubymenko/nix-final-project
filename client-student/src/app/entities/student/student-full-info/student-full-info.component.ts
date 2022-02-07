import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Student} from 'src/app/shared/models/user/student.model';

@Component({
  selector: 'student-full-info',
  templateUrl: './student-full-info.component.html',
  styleUrls: ['./student-full-info.component.scss']
})
export class StudentFullInfoComponent implements OnInit {

  student: Student;

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({student}) => {
      this.student = student;
    });
  }

}
