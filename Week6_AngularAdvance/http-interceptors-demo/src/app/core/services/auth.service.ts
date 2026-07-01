// src/app/core/services/auth.service.ts
import { Injectable, signal, computed } from '@angular/core';

const TOKEN_KEY = 'auth_token';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenSignal = signal<string | null>(localStorage.getItem(TOKEN_KEY));

  readonly token = computed(() => this.tokenSignal());
  readonly isAuthenticated = computed(() => !!this.tokenSignal());

  login(username: string, password: string): boolean {
    // In a real app this would call an API; here we simulate success.
    if (username && password) {
      const fakeToken = btoa(`${username}:${Date.now()}`);
      this.tokenSignal.set(fakeToken);
      localStorage.setItem(TOKEN_KEY, fakeToken);
      return true;
    }
    return false;
  }

  logout(): void {
    this.tokenSignal.set(null);
    localStorage.removeItem(TOKEN_KEY);
  }

  getToken(): string | null {
    return this.tokenSignal();
  }
}