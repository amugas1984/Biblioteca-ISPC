import {Component, Input, OnInit} from '@angular/core';
import {Transaction} from '../../Entities/transaction';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-book-owned-list-single',
  templateUrl: './book-owned-list-single.component.html',
  styleUrls: ['./book-owned-list-single.component.scss']
})
export class BookOwnedListSingleComponent implements OnInit {

  @Input() transaction: Transaction;
  path: string;
/*
  transaction1: Transaction = {
    id: null,
    borrower: new Student(),
    dateOfIssue: new Date(),
    returnDate: new Date("2019-09-30"),
  }*/
  constructor(private _DomSanitizerService: DomSanitizer) { }

  ngOnInit() {
    this.path="http://127.0.0.1:8887/";
  }

}
