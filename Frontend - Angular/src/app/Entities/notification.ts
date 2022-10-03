import {User} from './user';

export class Notification {
  id: number;
  message: string;
  createdAt: Date;
  recipient: User;
}
