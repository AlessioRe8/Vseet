/* import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule, ToastrService } from 'ngx-toastr';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AppRoutingModule } from './app.routes';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthService } from './services/auth/auth.service';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { CreateContentComponent } from './components/create-content/create-content/create-content.component';
import { POIDetailComponent } from './components/details-pages/poidetail/poidetail.component';
import { ItineraryDetailComponent } from './components/details-pages/itinerary-detail/itinerary-detail.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { ContributionsComponent } from './components/contributions/contributions.component';
import { EditContentComponent } from './components/edit-content/edit-content.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ItinerarySelectorComponent } from './components/itinerary-selector/itinerary-selector.component';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent,
    CreateContentComponent,
    POIDetailComponent,
    ItineraryDetailComponent,
    UserInfoComponent,
    ContactsComponent,
    ContributionsComponent,
    EditContentComponent,
    ItinerarySelectorComponent,
    PageNotFoundComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    MatDialogModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]

})
export class AppModule { }
 */