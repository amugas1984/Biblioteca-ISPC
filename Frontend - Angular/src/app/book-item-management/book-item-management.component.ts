import { Component, OnInit } from '@angular/core';
import {Book} from '../Entities/book';
import {BookItemService} from '../Services/book-item.service';
import {BookItem} from '../Entities/book-item';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-book-item-management',
  templateUrl: './book-item-management.component.html',
  styleUrls: ['./book-item-management.component.scss']
})
export class BookItemManagementComponent implements OnInit {

  constructor(private bookItemService:BookItemService,
              private router: Router,
              private location: Location,
              private snackBar: MatSnackBar) {
  }
  exist:boolean;
  added:boolean;

  ngOnInit() {
    this.added=false;
    this.exist=false;
  }

  click(book:Book,bookItemBarcode:string){
    var bookItemBarcodeInt= Number.parseInt(bookItemBarcode);

    (async () => {
      // Do something before delay
      this.checkBookItem(bookItemBarcodeInt);
      await this.delay(100);
      if (this.exist){
        alert("Ya hay una copia con ese codigo de barra");
        this.exist=false;
        return ;
      }
      if (book == null)
      {
        alert("Ingresa un codigo de libro valido");
        return;
      }
      var bookItem = new BookItem();
      bookItem.barcode = bookItemBarcodeInt;
      bookItem.book = book;

      this.addBookItem(bookItem);

      this.router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
      this.router.navigate([this.location.path()]));
    })();


  }


  checkBookItem(bookItemBarcode:number){
    this.bookItemService.getBookItem(bookItemBarcode).subscribe(
      res=>{
        this.exist=true;

      },
      error => {
        this.exist=false;
      }
    )
  }

  addBookItem(bookItem:BookItem){
    this.bookItemService.postBookItem(bookItem).subscribe(
      res=>{
        this.openSnackBar('Copy Añadida', 'Ok');
      },
      err=>{
        alert("algo anduvo mal");
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
