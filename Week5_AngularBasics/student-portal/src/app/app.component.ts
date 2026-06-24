import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent],
  template: `
    <app-navbar />
    <main class="container">
      <router-outlet />
    </main>
  `,
  styles: [`
    .container {
      max-width: 960px;
      margin: 2rem auto;
      padding: 0 1.5rem;
    }
  `],
})
export class AppComponent {}