import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFullInfoComponent } from './admin-full-info.component';

describe('AdminFullInfoComponent', () => {
  let component: AdminFullInfoComponent;
  let fixture: ComponentFixture<AdminFullInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminFullInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminFullInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
