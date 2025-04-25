import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';

@Component({
  selector: 'app-contacts',
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent {

  constructor( private toastr: CustomToastrService ) {}

  feedback = {
    email: '',
    message: ''
  };

  inviaFeedback() {
    console.log('Feedback inviato:', this.feedback);
    this.toastr.info('Feedback inviato', 'Grazie');
    this.feedback = { email: '', message: '' };
  }
}
