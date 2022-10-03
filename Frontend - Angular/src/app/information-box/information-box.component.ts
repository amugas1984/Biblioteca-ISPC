import { Component, OnInit } from '@angular/core';
import {TransactionService} from '../Services/transaction.service';
import {Transaction} from '../Entities/transaction';
import {BookItem} from '../Entities/book-item';
import {Student} from '../Entities/student';
import {formatDate, Location} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {StudentService} from '../Services/student.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-information-box',
  templateUrl: './information-box.component.html',
  styleUrls: ['./information-box.component.scss']
})
export class InformationBoxComponent implements OnInit {

  constructor(private transactionService: TransactionService,
              private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private snackBar: MatSnackBar) { }
  transaction: Transaction;
  action:string;
  nul: boolean ;
  added:boolean;
  returned:boolean;
  student: Student;
  ngOnInit() {
    this.nul=false;
    this.added=false;
    this.returned=false;
    this.transaction = new Transaction();
    this.student = new Student();
    this.action=this.route.snapshot.paramMap.get('action');
    //if param changes the method is executed
    this.route.params
      .subscribe(params => this.handleRouteChange(params));

  }
  public handleRouteChange(params){
    this.action=params['action'];
  }

  issueBook(student: Student, bookItem:BookItem){
      if (student == null){
        alert("Por favor ingresa un Id de estudiante valido");
        return;
      }
      else if (bookItem == null){
        alert("Por favor ingresa un codigo valido de libro");
        return;
      }
      if (!bookItem.book.available)
      {
        alert("El Libro no esta disponible en este momento");
        return;
      }
      for (var transaction of student.transactions){
        if (transaction.book.isbn == bookItem.book.isbn && !transaction.returned)
        {
          alert("Estudiante ya tiene el libro");
          return;
        }
      }
      this.transaction.borrower = student;
      this.transaction.bookItem = bookItem;
      this.transaction.book = bookItem.book;
      this.transaction.dateOfIssue = new Date();
      this.transaction.returnDate = new Date(new Date().setMonth(new Date().getMonth() + 1));

      (async () => {
        // Do something before delay
        this.postTransaction(this.transaction);
        await this.delay(100);
        // Do something after
        this.router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
          this.router.navigate([this.location.path()]));
        console.log('after delay');

      })();
  }
  returnBook(student: Student, bookItem:BookItem){
    if (student == null){
      alert("Por favor ingresa un Id de estudiante valido");
      return;
    }
    else if (bookItem == null){
      alert("Por favor ingresa un codigo valido de libro");
      return;
    }

    this.transaction.borrower = student;
    this.transaction.bookItem = bookItem;
    this.transaction.book = bookItem.book;
    this.transaction.dateOfIssue = new Date();
    this.transaction.returnDate = new Date(new Date().setMonth(new Date().getMonth() + 1));


    (async () => {
      this.getTransactionToReturn(this.transaction.borrower.uid,this.transaction.bookItem.barcode);
      await this.delay(100);
      if (this.nul){
        alert("El estudiante no posee el libro seleccionado");
        return;
      }
      this.getStudentFromTransaction(this.transaction.id);
      await this.delay(100);

      this.transaction.borrower = this.student;
      await this.delay(100);
      this.updateBook();

      this.router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
        this.router.navigate([this.location.path()]));
    })();
  }

  getTransactionToReturn(studentId:number, bookItemBarcode:number){
    this.transactionService.getTransactionToReturn(studentId,bookItemBarcode).subscribe(
      res => {
        this.transaction = res;
        this.transaction.id = res.id;
      },
      err => {
        this.nul=true;
      }
    )
  }

  getStudentFromTransaction(idTransaction:number){
    this.studentService.getStudentFromTransaction(idTransaction).subscribe(
      res=>{
        this.student.uid = res.uid;
      },
      err=>{
      }
    )
  }

  updateBook(){
    this.transactionService.updateTransaction(this.transaction).subscribe(
      res => {
        this.openSnackBar('Book Returned', 'Okay');
      },
      err => {
        alert("Algo salio mal");
      }
    )
  }
  postTransaction(transaction:Transaction){
    this.transactionService.postTransaction(this.transaction).subscribe(
      res => {
        this.openSnackBar('Book Issued', 'Okay');
      },
      err => {
        alert("Algo salio mal");
      }
    )
  }

  openSnackBar(message: string, action: string){
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

}
