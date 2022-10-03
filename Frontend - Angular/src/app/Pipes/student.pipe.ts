import { Pipe, PipeTransform } from '@angular/core';
import {Student} from '../Entities/student';

@Pipe({
  name: 'student'
})
export class StudentPipe implements PipeTransform {

  transform(student: any):any {
    return "Name: "+ student.firstName+" "+student.lastName;
  }

}
