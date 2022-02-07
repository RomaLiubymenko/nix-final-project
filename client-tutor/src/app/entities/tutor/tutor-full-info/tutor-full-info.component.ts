import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Tutor} from "../../../shared/models/user/tutor.model";

@Component({
  selector: 'tutor-full-info',
  templateUrl: './tutor-full-info.component.html',
  styleUrls: ['./tutor-full-info.component.scss']
})
export class TutorFullInfoComponent implements OnInit {

  tutor: Tutor;

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({tutor}) => {
      this.tutor = tutor;
    });
  }

}
