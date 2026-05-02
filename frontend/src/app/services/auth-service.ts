import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly storageKey = 'basicAuthCreds';
  private _isAuthenticated = signal<boolean>(false);

  constructor() {
    const saved = sessionStorage.getItem(this.storageKey);
    this._isAuthenticated.set(!!saved);
  }

  isAuthenticated() {
    return this._isAuthenticated();
  }

  authChange() {
    return this._isAuthenticated.asReadonly();
  }

  getAuthorizationHeader(): string | null {
    const saved = sessionStorage.getItem(this.storageKey);
    return saved ? `Basic ${saved}` : null;
  }

  login(username: string, password: string) {
    const token = btoa(`${username}:${password}`);
    sessionStorage.setItem(this.storageKey, token);
    this._isAuthenticated.set(true);
  }

  logout() {
    sessionStorage.removeItem(this.storageKey);
    this._isAuthenticated.set(false);
  }
}
