import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationBoxSingleComponent } from './information-box-single.component';

describe('InformationBoxSingleComponent', () => {
  let component: InformationBoxSingleComponent;
  let fixture: ComponentFixture<InformationBoxSingleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InformationBoxSingleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InformationBoxSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
