import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BookItem} from '../Entities/book-item';
import {Book} from '../Entities/book';
import {Student} from '../Entities/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private BASE_URL = 'http://localhost:8080/api/student';
  public  ALL_STUDENTS_URL = `${this.BASE_URL}/all`;
  private GET_STUDENT_URL = `${this.BASE_URL}/`;
  private GET_STUDENT_FROM_TRANSACTION_URL = `${this.BASE_URL}/fromTransaction/`;
  private ADD_UPDATE_STUDENT_URL = `${this.BASE_URL}`;
  private DELETE_STUDENT_URL = `${this.BASE_URL}/`;


  constructor(private http: HttpClient) {

  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.ALL_STUDENTS_URL);
  }

  getStudent(id:number): Observable<Student> {
    return this.http.get<Student>(this.GET_STUDENT_URL + id);
  }

  getStudentFromTransaction(idTransaction:number): Observable<Student> {
    return this.http.get<Student>(this.GET_STUDENT_FROM_TRANSACTION_URL + idTransaction);
  }

  /*  getNumberofBooksAvailble(id:number): Observable<any> {
      return this.http.get<any>(this.AVAILBLE_BOOK_URL + id);
    }*/

  postStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.ADD_UPDATE_STUDENT_URL, student);
  }

  deleteStudent(id: number): Observable<any> {
    return this.http.delete(this.DELETE_STUDENT_URL + id);
  }
}
