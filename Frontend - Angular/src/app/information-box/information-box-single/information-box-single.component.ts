import {Component, Input, OnInit} from '@angular/core';
import {BookItemService} from '../../Services/book-item.service';
import {StudentService} from '../../Services/student.service';
import {Student} from '../../Entities/student';
import {BookItem} from '../../Entities/book-item';
import {FormControl} from '@angular/forms';
import {Book} from '../../Entities/book';
import {BookService} from '../../Services/book.service';

@Component({
  selector: 'app-information-box-single',
  templateUrl: './information-box-single.component.html',
  styleUrls: ['./information-box-single.component.scss']
})
export class InformationBoxSingleComponent implements OnInit {

  @Input() objectInput : string;
  object: any ;
  student= false;
  bookItem = false;
  book = false;
  objectId = new FormControl('');
  objectInformation = new FormControl('');

  constructor(private studentService : StudentService, private bookItemService :BookItemService, private bookService: BookService) { }

  ngOnInit() {
  }


  getObject(objectInput:string){
    if (objectInput=="Student")
    {
      (async () => {
        this.object = new Student();
        this.student=true;
        console.log('before delay');
        this.getStudent(this.objectId.value);
        await this.delay(100);
        console.log('after delay');
        this.click();

      })();
    }
    else if (objectInput=="BookItem")
    { (async () => {
      this.object = new BookItem();
      this.bookItem=true;
      console.log('before delay');
      this.getBookItem(this.objectId.value);
      await this.delay(100);
      console.log('after delay');
      this.click();
    })();
    }
    else if (objectInput=="Book")
    { (async () => {
      this.object = new Book();
      this.book=true;
      console.log('before delay');
      this.getBook(this.objectId.value);
      await this.delay(100);
      console.log('after delay');
      this.click();
    })();
    }
  }
  click(){
    if (this.object==null)
      return;
    if (this.student) {
    this.objectInformation.setValue("Name: "+this.object.firstName + " "+this.object.lastName+"\n\n" +
      "Email: "+this.object.email+"\n\n" +
      "Phone Number: "+this.object.phoneNumber);
    }
      else if (this.bookItem) {
      this.objectInformation.setValue("Title: " + this.object.book.title + "\n\n" +
        "Author: " + this.object.book.author + "\n\n" +
        "Category: " + this.object.book.category);
    }
    else if (this.book) {
      this.objectInformation.setValue("Title: " + this.object.title + "\n\n" +
        "Author: " + this.object.author + "\n\n" +
        "Category: " + this.object.category);
    }

  }

  public getStudent(studentId:number) {
    this.studentService.getStudent(studentId).subscribe(
      res => {
        this.object = res;
      },
      err => {
        this.object=null;
        alert("Estudiante no encontrado");
      }
    );
  }

  public getBookItem(bookItemBarCode:number) {
    this.bookItemService.getBookItem(bookItemBarCode).subscribe(
      res => {
        this.object = res;
      },
      err => {
        this.object=null;
        alert("Libro no encontrado")
      }
    );
  }

  public getBook(bookId:number) {
    this.bookService.getBook(bookId).subscribe(
      res => {
        this.object = res;
      },
      err => {
        this.object=null;
        alert("Libro no encontrado")
      }
    );
  }

delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
