import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../Entities/book';
import {BookItem} from '../Entities/book-item';

@Injectable({
  providedIn: 'root'
})
export class BookItemService {

  private BASE_URL = 'http://localhost:8080/api/bookItem';
  public  ALL_BOOKITEMS_URL = `${this.BASE_URL}/all`;
  private GET_BOOKITEM_URL = `${this.BASE_URL}/`;
  private ADD_UPDATE_BOOKITEM_URL = `${this.BASE_URL}`;
  private DELETE_BOOKITEM_URL = `${this.BASE_URL}/`;
  private AVAILBLE_BOOKITEM_URL = `${this.BASE_URL}/available/`;


  constructor(private http: HttpClient) {

  }

  getAllBookItems(): Observable<BookItem[]> {
    return this.http.get<BookItem[]>(this.ALL_BOOKITEMS_URL);
  }

  getBookItem(id:number): Observable<BookItem> {
    return this.http.get<BookItem>(this.GET_BOOKITEM_URL + id);
  }

/*  getNumberofBooksAvailble(id:number): Observable<any> {
    return this.http.get<any>(this.AVAILBLE_BOOK_URL + id);
  }*/

  postBookItem(bookItem: BookItem): Observable<BookItem> {
    return this.http.post<BookItem>(this.ADD_UPDATE_BOOKITEM_URL, bookItem);
  }

  deleteBookItem(id: number): Observable<any> {
    return this.http.delete(this.DELETE_BOOKITEM_URL + id);
  }

}
