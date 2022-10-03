import { Component, OnInit } from '@angular/core';
import {Book} from '../Entities/book';
import {BookService} from '../Services/book.service';
import {NavbarComponent} from '../navbar/navbar.component';
import {HttpClient} from '@angular/common/http';
import {FormControl, FormsModule, Validators} from '@angular/forms';
import {Category} from '../Entities/category';
import {MatSnackBar} from '@angular/material';
import {EmbeddedBookFile} from '../Entities/embedded-book-file';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {

  constructor(private bookService:BookService,
              private snackBar: MatSnackBar) { }
  categoryControl = new FormControl('', [Validators.required]);
  book: Book;
  categories: string[] =[];
  imageSaved:boolean;
  image: File;

  ngOnInit() {
    this.book = new Book();
    this.categories = this.bookService.getCategories();
  }

  getCategorySelected(category: string){
    for (var categoryTemp in Category){
      if(categoryTemp.toString()==category)
        this.book.category= Category[category];
    }
  }

  onSubmit() {

    this.postImage(this.image);
    if(!this.imageSaved) {
      alert("Image is too Big");
      return;
    }
    this.book.image="images/"+this.image.name;
    this.bookService.postBook(this.book).subscribe(
      res => {
        this.openSnackBar('Book Added', 'Okay');
      },
      err => {
        alert("An error has occurred;")
      }
    );
  }

  postImage(image: File) {
    this.bookService.postImage(image).subscribe(
      res=>{
        this.imageSaved=res;
      },
      err=>{
        console.log("Something went wrong book");
      }
    )
}

  onSelectedFile(event){
    this.image=<File>event.target.files[0];
  }

  openSnackBar(message: string, action: string){
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
