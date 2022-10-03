import { Component, OnInit } from '@angular/core';
import {Book} from '../Entities/book';
import {BookService} from '../Services/book.service';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../Entities/user';
import {AuthenticationService} from '../Services/authentication.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  books: Book[] = [] ;
  category:string;
  searchText:string;
  loading=true;
  currentUser: User;
  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService ){
    this.currentUser = this.authenticationService.currentUserValue;
  }

  addItem(book: Book) {
    this.books.push(book);
  }

  ngOnInit() {
    this.loading=true;
    //To hande route change in the event the parameter is edited
    this.route.params
      .subscribe(params => this.handleRouteChange(params));
  }

  public handleRouteChange(params){
    this.category=params['category'];
    if(this.category==null)
      this.getAllBooks();
    else {
      this.getBooksByCategory();
      console.log(this.books);
    }
  }

  public getAllBooks() {
    this.bookService.getAllBooks().subscribe(
      res => {
        this.books = res;
      },
      err => {
        alert("An error has occurred;")
      }
    );
  }

  public getBooksByCategory() {
    this.bookService.getBooksByCategory(this.category).subscribe(
      res => {
        this.books = res;
      },
      err => {
        alert("An error has occurred;")
      }
    );
  }

}
