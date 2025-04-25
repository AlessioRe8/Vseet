import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../../services/user/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-info',
  imports: [CommonModule],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent implements OnInit {
  user!: User;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserDetails().subscribe(
      (data) => this.user = data,
      (error) => console.error('Errore nel recupero dei dati utente:', error)
    );
  }
}
