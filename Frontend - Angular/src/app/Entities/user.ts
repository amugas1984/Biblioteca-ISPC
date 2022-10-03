import {Transaction} from './transaction';
import {Notification} from './notification';
import {Role} from './role.enum';

export class User {
  uid: number;
  password: string;
  login: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: number;
  notifications: Array<Notification>;
  role:string;

}

