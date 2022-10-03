import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookItemManagementComponent } from './book-item-management.component';

describe('BookItemManagementComponent', () => {
  let component: BookItemManagementComponent;
  let fixture: ComponentFixture<BookItemManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookItemManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookItemManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
