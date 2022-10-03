import { Component, OnInit } from '@angular/core';
import {Book} from '../Entities/book';
import {BookService} from '../Services/book.service';
import {TransactionService} from '../Services/transaction.service';
import {Transaction} from '../Entities/transaction';
import {AuthenticationService} from '../Services/authentication.service';
import {filter, map} from 'rxjs/operators';

@Component({
  selector: 'app-book-owned-list',
  templateUrl: './book-owned-list.component.html',
  styleUrls: ['./book-owned-list.component.scss']
})
export class BookOwnedListComponent implements OnInit {

  books: Book[] = [] ;
  transactions: Transaction[] = [] ;
  studentId = this.authenticationService.currentUserValue.uid;

  constructor(private transactionService: TransactionService,private authenticationService: AuthenticationService) { }

  addItem(book: Book) {
    this.books.push(book);
  }

  getBooksFromTransactions(transactions: Transaction[]) {
    for (var transaction in transactions){
      this.addItem(transactions[transaction].book);
    }
  }

  ngOnInit() {
    this.getAllTransaction();
    console.log(this.books);
  }

  public getAllTransaction() {
    this.transactionService.getAllTransaction(this.studentId).pipe(map(tr=> tr.filter(
      trsingle=> !trsingle.returned
    ))).subscribe(
      res => {
        this.transactions = res;
      },
      err => {
        alert("An error has occurred;")
      }
    );
  }

}
