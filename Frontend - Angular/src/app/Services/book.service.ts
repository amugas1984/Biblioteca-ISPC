import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../Entities/book';
import {Category} from '../Entities/category';
import {EmbeddedBookFile} from '../Entities/embedded-book-file';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private BASE_URL = 'http://localhost:8080/api/book';
  public ALL_BOOKS_URL = `${this.BASE_URL}/all`;
  private GET_BOOK_URL = `${this.BASE_URL}/`;
  private GET_PATH_BOOK_URL = `${this.BASE_URL}/path`;
  private ADD_UPDATE_BOOK_URL = `${this.BASE_URL}`;
  private DELETE_BOOK_URL = `${this.BASE_URL}/`;
  private AVAILBLE_BOOK_URL = `${this.BASE_URL}/available/`;
  private BOOK_BY_CATEGORY_URL = `${this.BASE_URL}/category/`;
  private ADD_UPDATE_IMAGE_URL = `${this.BASE_URL}/image`;

  category : string[] = [];
  constructor(private http: HttpClient) {

  }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.ALL_BOOKS_URL);
  }

  getBook(id: number): Observable<Book> {
    return this.http.get<Book>(this.GET_BOOK_URL + id);
  }

  getPath(){
    return this.http.get(this.GET_PATH_BOOK_URL,{ responseType: 'text' });
  }

  getNumberofBooksAvailble(id: number): Observable<any> {
    return this.http.get<any>(this.AVAILBLE_BOOK_URL + id);
  }

  postBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.ADD_UPDATE_BOOK_URL, book);
  }

  postImage(image: File):Observable<boolean> {
    const formdata: FormData = new FormData();
    formdata.append('file', image);
    return this.http.post<boolean>(this.ADD_UPDATE_IMAGE_URL, formdata);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(this.DELETE_BOOK_URL + id);
  }

  getBooksByCategory(category: string): Observable<Book[]> {
    return this.http.get<Book[]>(this.BOOK_BY_CATEGORY_URL + category);
  }

  getCategories():string[] {
    var size = 0;
    for (let category in Category) {

      (async () => {
        if (isNaN(Number(category))) {
          await this.delay(1000);
          this.category.push(category);
          size++;
        }
      })();

    }
    return this.category;
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
