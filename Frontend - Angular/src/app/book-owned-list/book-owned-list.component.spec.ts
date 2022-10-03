import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookOwnedListComponent } from './book-owned-list.component';

describe('BookOwnedListComponent', () => {
  let component: BookOwnedListComponent;
  let fixture: ComponentFixture<BookOwnedListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookOwnedListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookOwnedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
