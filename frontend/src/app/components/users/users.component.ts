import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [ CommonModule, FormsModule ],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  users: any[] = [];

  constructor(private userService: UserService, private toastr: CustomToastrService) {}

  ngOnInit() {
    this.userService.getAllUsers().subscribe(users => this.users = users);
  }

  updateRole(userId: number, newRole: string) {
    this.userService.editUserRole(userId, newRole).subscribe(() => {
      this.toastr.success('Ruolo aggiornato!');
      this.ngOnInit();
    });
  }
}
