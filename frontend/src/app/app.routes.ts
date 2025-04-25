import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { CreateContentComponent } from './components/create-content/create-content/create-content.component';
import { POIDetailComponent } from './components/details-pages/poidetail/poidetail.component';
import { ItineraryDetailComponent } from './components/details-pages/itinerary-detail/itinerary-detail.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { ContributionsComponent } from './components/contributions/contributions.component';
import { EditContentComponent } from './components/edit-content/edit-content.component';
import { UsersComponent } from './components/users/users.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'user-info', component: UserInfoComponent },
  { path: 'poi/:id', component: POIDetailComponent },
  { path: 'itinerary/:id', component: ItineraryDetailComponent },
  { path: 'create-content', component: CreateContentComponent },
  { path: 'contacts', component: ContactsComponent},
  { path: 'users', component: UsersComponent },
  { path: 'contributions', component: ContributionsComponent},
  { path: 'edit-content/:id', component: EditContentComponent},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
