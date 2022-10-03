import {Component, Input, OnInit} from '@angular/core';
import {Category} from '../Entities/category';
import {Role} from '../Entities/role.enum';
import {AuthenticationService} from '../Services/authentication.service';
import {Router} from '@angular/router';
import {User} from '../Entities/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {}
  @Input() role:string;
  size:number;
  roleEnum: Role;
  category: string[] =[];
  ngOnInit() {
    this.getCategories();

   }

   getCategories(){
     this.size=0;
     for (let category in Category) {
       (async () => {
         if (isNaN(Number(category))) {
           await this.delay(1000);
           this.category.push(category);
           this.size++;

         }})();

     }
   }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

delay(ms: number) {
  return new Promise( resolve => setTimeout(resolve, ms) );
}

}
