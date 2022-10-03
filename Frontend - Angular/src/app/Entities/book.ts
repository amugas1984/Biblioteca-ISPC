import {BookItem} from './book-item';
import {Transaction} from './transaction';
import {BookService} from '../Services/book.service';
import {Category} from './category';

export class Book {
   isbn: number;
   title: string;
   description: string;
   author: string;
   publisher: string ;
   edition: string ;
   image: string;
   available:boolean;
   category:Category;
   numberOfPages: number;
   language: string;
   bookItems: Array<BookItem>;
   transactions: Array<Transaction>;

  constructor() {
  }

}
