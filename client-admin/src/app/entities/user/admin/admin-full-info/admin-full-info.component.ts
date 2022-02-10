import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Admin} from 'src/app/shared/models/user/admin.model';

@Component({
  selector: 'admin-full-info',
  templateUrl: './admin-full-info.component.html',
  styleUrls: ['./admin-full-info.component.scss']
})
export class AdminFullInfoComponent implements OnInit {

  admin: Admin;

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({admin}) => {
      this.admin = admin;
    });
  }

}
