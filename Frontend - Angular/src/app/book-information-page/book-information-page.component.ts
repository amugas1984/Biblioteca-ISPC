import { Component, OnInit } from '@angular/core';
import {BookService} from '../Services/book.service';
import {Book} from '../Entities/book';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-book-information-page',
  templateUrl: './book-information-page.component.html',
  styleUrls: ['./book-information-page.component.scss']
})
export class BookInformationPageComponent implements OnInit {

  book: Book;
  items : string[] =[] ;
  isbn: number;
  path: string;
  numberOfBooksAvailable:number;
  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private _DomSanitizerService: DomSanitizer,
              private router: Router) { }

  ngOnInit() {
    this.path="http://127.0.0.1:8887/";
    (async () => {
      // Do something before delay
      console.log('before delay')
      this.getBook(Number.parseInt(this.route.snapshot.paramMap.get('isbn')));
      await this.delay(1000);
      this.getNumberAvailable(this.book.isbn);
      // Do something after
      console.log('after delay')
      this.replaceDescription();
      console.log(this.book.description);
    })();
  }

  replaceDescription() {
    this.items.push(this.book.description);
    this.items.map(i => i.replace(/\n/g, ' <br/> '));
    this.book.description=this.items[0];
  }

  getNumberAvailable(id: number){
    this.bookService.getNumberofBooksAvailble(id).subscribe(
      res => {
        this.numberOfBooksAvailable = res;
      },
      err => {
        alert("Ocurrio un error")
      }
    );
  }

  getBook(isbn:number){
    this.bookService.getBook(isbn).subscribe(
      res => {
        this.book = res;
        //console.log(this.book.description)
      },
      err => {
        alert("Ocurrio un error")
      }
    );
  }
  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
