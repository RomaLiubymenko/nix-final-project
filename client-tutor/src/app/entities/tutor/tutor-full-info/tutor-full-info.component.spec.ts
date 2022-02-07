import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TutorFullInfoComponent } from './tutor-full-info.component';

describe('TutorFullInfoComponent', () => {
  let component: TutorFullInfoComponent;
  let fixture: ComponentFixture<TutorFullInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TutorFullInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TutorFullInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
