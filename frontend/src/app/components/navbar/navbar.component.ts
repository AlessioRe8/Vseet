import { Component, HostListener } from '@angular/core';
import { Router, RouterLink} from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user/user.service';
@Component({
  imports: [RouterLink, CommonModule],
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(public authService: AuthService, private router: Router, private userService: UserService) {}
  dropdownOpen = false;
  isAuthenticated = false;
  accountSubmenuOpen = false;
  mobileMenuOpen = false;
  userRole: string | null = null;


  ngOnInit() {
    this.isAuthenticated = this.authService.isAuthenticated();
    if (this.isAuthenticated == true) { 
      this.userService.getUserDetails().subscribe({
        next: (user) => this.userRole = user.role,
        error: (err) => console.error('Errore nel recuperare il ruolo utente', err)
      });
    }
  }
  
  goToUserInfo() {
    this.router.navigate(['/user-info']);
    this.dropdownOpen = false;
  }

  canManageUsers(): boolean {
    if (this.isAuthenticated == false) {
      return false;
    }
    return this.userRole === 'PLATFORM_MANAGER';
    }

  logout(): void {
    this.authService.logout();
    this.isAuthenticated = false;
    this.router.navigateByUrl('/home');
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  toggleMobileMenu() {
    this.mobileMenuOpen = !this.mobileMenuOpen;
    this.accountSubmenuOpen = false;
    this.dropdownOpen = false;
  }

  toggleAccountSubmenu() {
    this.accountSubmenuOpen = !this.accountSubmenuOpen;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    const w = event.target.innerWidth;
    if (w > 768 && this.mobileMenuOpen) {
      this.mobileMenuOpen = false;
      this.accountSubmenuOpen = false;
    }
  }

}

