import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  template: `
    <nav class="navbar">
      <span class="brand">🎓 Student Portal</span>
      <div class="links">
        <a routerLink="/students" routerLinkActive="active" [routerLinkActiveOptions]="{ exact: true }">Students</a>
        <a routerLink="/students/new" routerLinkActive="active">Add Student</a>
      </div>
    </nav>
  `,
  styles: [`
    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 2rem;
      background: #1a1a2e;
      color: white;
    }
    .brand { font-size: 1.25rem; font-weight: 700; letter-spacing: 0.5px; }
    .links { display: flex; gap: 1.5rem; }
    .links a { color: #a0aec0; text-decoration: none; font-size: 0.95rem; transition: color 0.2s; }
    .links a:hover, .links a.active { color: #63b3ed; }
  `],
})
export class NavbarComponent {}