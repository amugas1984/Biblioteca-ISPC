import {Transaction} from './transaction';
import {User} from './user';

export class Student extends User {

  transactions: Array<Transaction>;


  toString(): string {
    return "UID "+this.uid+"\n" +
      "Email "+this.email;
  }
}

