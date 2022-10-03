import { Pipe, PipeTransform } from '@angular/core';
import {Book} from '../Entities/book';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(books: Book[], textSearch: string): any {
    if(textSearch == null || textSearch === ""){
      return books;
    }
    return books.filter(b => b.title.toLowerCase().includes(textSearch.toLowerCase()) || b.author.toLowerCase().includes(textSearch.toLowerCase()));
  }

}
