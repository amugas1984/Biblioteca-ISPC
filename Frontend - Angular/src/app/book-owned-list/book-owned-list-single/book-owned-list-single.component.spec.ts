import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookOwnedListSingleComponent } from './book-owned-list-single.component';

describe('BookOwnedListSingleComponent', () => {
  let component: BookOwnedListSingleComponent;
  let fixture: ComponentFixture<BookOwnedListSingleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookOwnedListSingleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookOwnedListSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
