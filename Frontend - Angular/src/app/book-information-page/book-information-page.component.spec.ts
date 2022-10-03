import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookInformationPageComponent } from './book-information-page.component';

describe('BookInformationPageComponent', () => {
  let component: BookInformationPageComponent;
  let fixture: ComponentFixture<BookInformationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookInformationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookInformationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
