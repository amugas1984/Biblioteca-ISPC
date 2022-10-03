import {NgModule, Pipe, PipeTransform} from '@angular/core';
import {StudentPipe} from './student.pipe';

@Pipe({
  name: 'dynamic'
})

export class DynamicPipe implements PipeTransform {

  public constructor() {
  }

  transform(value: any):any {
    if (0) {
      console.log("bbbbbbb"+value);
      return value;
    }
    else {
      var pipe =  new StudentPipe();
      return pipe.transform(value);
    }
  }

}
