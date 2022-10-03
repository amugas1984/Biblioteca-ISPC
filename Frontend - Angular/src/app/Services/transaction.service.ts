import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from '../Entities/book';
import {Transaction} from '../Entities/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private BASE_URL = 'http://localhost:8080/api/transaction';
  public  ALL_TRANSACTIONS_URL = `${this.BASE_URL}/student/`;
  private GET_TRANSACTION_URL = `${this.BASE_URL}/`;
  private GET_TRANSACTION_TO_RETURN = `${this.BASE_URL}/`;
  private ADD_UPDATE_TRANSACTION_URL = `${this.BASE_URL}`;
  private DELETE_TRANSACTION_URL = `${this.BASE_URL}/`;

  constructor(private http: HttpClient) {

  }

  getAllTransaction(studentId:number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.ALL_TRANSACTIONS_URL+studentId);
  }

  getTransaction(id:number): Observable<Transaction> {
    return this.http.get<Transaction>(this.GET_TRANSACTION_URL + id);
  }

  getTransactionToReturn(studentId:number, bookItemBarcode:number): Observable<Transaction> {
    return this.http.get<Transaction>(this.GET_TRANSACTION_URL + studentId + "/" + bookItemBarcode);
  }

  postTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(this.ADD_UPDATE_TRANSACTION_URL, transaction);
  }

  updateTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.put<Transaction>(this.ADD_UPDATE_TRANSACTION_URL, transaction);
  }

  deleteTransaction(id: number): Observable<any> {
    return this.http.delete(this.DELETE_TRANSACTION_URL + id);
  }
}
