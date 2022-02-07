import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentFullInfoComponent } from './admin-full-info.component';

describe('AdminFullInfoComponent', () => {
  let component: StudentFullInfoComponent;
  let fixture: ComponentFixture<StudentFullInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentFullInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentFullInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
