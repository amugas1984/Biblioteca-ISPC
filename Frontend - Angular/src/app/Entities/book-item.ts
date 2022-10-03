import {Book} from './book';

export class BookItem {
   barcode: number;
   book: Book;


  toString(): string {
    return "Book Title "+this.book.title+"\n" +
      "Category "+this.book.category;
  }
}
