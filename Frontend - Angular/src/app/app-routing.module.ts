import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BookOwnedListComponent} from './book-owned-list/book-owned-list.component';
import {BookFormComponent} from './book-form/book-form.component';
import {BookItemManagementComponent} from './book-item-management/book-item-management.component';
import {InformationBoxComponent} from './information-box/information-box.component';
import {BookInformationPageComponent} from './book-information-page/book-information-page.component';
import {BookListComponent} from './book-list/book-list.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {AuthGuard} from './_helpers/auth.guard';
import {Role} from './Entities/role.enum';
import {LoginComponent} from './login/login.component';


const appRoutes : Routes = [
  {
    path: '',
    component: BookListComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
    data:{roles :["Librarian",'Student']}
  },
  {
    path: 'librarian/book/add',
    component: BookFormComponent
  },
  {
    path: 'librarian/addBookItem',
    component: BookItemManagementComponent
  },
  {
    path: 'librarian/transaction/:action',
    component: InformationBoxComponent
  },
  {
    path: 'student/owned',
    component: BookOwnedListComponent
  },
  {
    path: 'book/:isbn',
    component: BookInformationPageComponent
  },
  {
    path: 'book/category/:category',
    component: BookListComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '**',
    redirectTo:''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const appRoutingModule = RouterModule.forRoot(appRoutes);
