import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BookService} from '../../Services/book.service';
import {DomSanitizer} from '@angular/platform-browser';
import {Book} from '../../Entities/book';

@Component({
  selector: 'app-book-list-single',
  templateUrl: './book-list-single.component.html',
  styleUrls: ['./book-list-single.component.scss']
})
export class BookListSingleComponent implements OnInit {

  @Input() book: Book ;
  numberOfBooksAvailable:number;
  path:string;
  constructor(private bookService: BookService,
              private _DomSanitizerService: DomSanitizer) { }

  ngOnInit() {
    this.getNumberAvailable(this.book.isbn);
    //this.getPath();
    this.path="http://127.0.0.1:8887/";
  }

  getNumberAvailable(id: number){
    this.bookService.getNumberofBooksAvailble(id).subscribe(
        res => {
          this.numberOfBooksAvailable = res;
        },
        err => {
          alert("An error has occurred;")
        }
      );
  }
  getPath(){
    this.bookService.getPath().subscribe(
      res => {
        this.path = res;
      },
      err => {
        alert("An error has occurred try later;")
      }
    );
  }


}
