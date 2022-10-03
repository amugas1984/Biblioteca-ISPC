import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookListSingleComponent } from './book-list-single.component';

describe('BookListSingleComponent', () => {
  let component: BookListSingleComponent;
  let fixture: ComponentFixture<BookListSingleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookListSingleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookListSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
