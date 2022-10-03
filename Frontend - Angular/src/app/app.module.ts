import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';

import {MatButtonModule} from '@angular/material/button';
import {
  MatCardModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatSelectModule,
  MatSnackBar, MatSnackBarModule,
  MatToolbarModule
} from '@angular/material';
import { NavbarComponent } from './navbar/navbar.component';
import { BookInformationPageComponent } from './book-information-page/book-information-page.component';
import { BookListComponent } from './book-list/book-list.component';
import { BookOwnedListComponent } from './book-owned-list/book-owned-list.component';
import { BookFormComponent } from './book-form/book-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BookListSingleComponent } from './book-list/book-list-single/book-list-single.component';
import { BookOwnedListSingleComponent } from './book-owned-list/book-owned-list-single/book-owned-list-single.component';
import {HttpClientModule} from '@angular/common/http';
import { NotFoundComponent } from './not-found/not-found.component';
import { InformationBoxComponent } from './information-box/information-box.component';
import { InformationBoxSingleComponent } from './information-box/information-box-single/information-box-single.component';
import { StudentPipe } from './Pipes/student.pipe';
import {DynamicPipe} from './Pipes/dynamic.pipe';
import { SearchPipe } from './Pipes/search.pipe';
import { BookItemManagementComponent } from './book-item-management/book-item-management.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    BookInformationPageComponent,
    BookListComponent,
    BookOwnedListComponent,
    BookFormComponent,
    BookListSingleComponent,
    BookOwnedListSingleComponent,
    NotFoundComponent,
    InformationBoxComponent,
    InformationBoxSingleComponent,
    DynamicPipe,
    StudentPipe,
    DynamicPipe,
    SearchPipe,
    BookItemManagementComponent,
    LoginComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
