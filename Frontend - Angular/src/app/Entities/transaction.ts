import {Student} from './student';
import {Book} from './book';
import {BookItem} from './book-item';

export class Transaction {
  id: number;
  dateOfIssue: Date;
  returnDate: Date;
  bookItem: BookItem;
  book : Book;
  borrower: Student;
  returned:boolean;
}

