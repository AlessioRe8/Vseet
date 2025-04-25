import { Injectable } from '@angular/core';
import { ToastrService as NgxToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class CustomToastrService {

  constructor(private toastr: NgxToastrService) { }

  success(message: string, title: string = 'Successo') {
    this.toastr.success(message, title);
  }

  error(message: string, title: string = 'Errore') {
    this.toastr.error(message, title);
  }

  warning(message: string, title: string = 'Attenzione') {
    this.toastr.warning(message, title);
  }

  info(message: string, title: string = 'Info') {
    this.toastr.info(message, title);
  }
}
