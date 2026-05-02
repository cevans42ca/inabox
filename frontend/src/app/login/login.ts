import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../services/auth-service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <section class="content" style="max-width: 480px; margin: 2rem auto;">
      <h2>Login</h2>
      <form (ngSubmit)="onSubmit()" #f="ngForm">
        <div style="margin-bottom: 1rem;">
          <label>Username<br>
            <input name="username" [(ngModel)]="username" required />
          </label>
        </div>
        <div style="margin-bottom: 1rem;">
          <label>Password<br>
            <input type="password" name="password" [(ngModel)]="password" required />
          </label>
        </div>
        <div style="color: #b00020;" *ngIf="error()">{{ error() }}</div>
        <button type="submit" class="primary">Login</button>
      </form>
    </section>
  `
})
export class LoginComponent {
  private readonly router = inject(Router);
  private readonly http = inject(HttpClient);
  private readonly auth = inject(AuthService);

  username = '';
  password = '';
  error = signal<string | null>(null);

  onSubmit() {
    // Prepare temporary Basic header to test credentials with a lightweight call
    const tempHeader = 'Basic ' + btoa(`${this.username}:${this.password}`);
    // We only care about status; avoid JSON parsing errors (e.g., if a proxy/html
    // page is ever returned with status 200). Request as text and observe the
    // full response so 200 never routes to error due to JSON parse.
    this.http.get('/api/overview', {
      headers: { Authorization: tempHeader } as any,
      responseType: 'text',
      observe: 'response'
    })
      .subscribe({
        next: (resp) => {
          console.log('[DEBUG_LOG] LoginComponent: test call status =', resp.status);
          this.auth.login(this.username, this.password);
          // Optional diagnostic to verify success path is executed
          console.log('[DEBUG_LOG] LoginComponent: login success, navigating to /');
          // Clear any previous error on successful login
          this.error.set(null);
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.log('[DEBUG_LOG] LoginComponent: login error', err);
          if (err?.status === 401) {
            this.error.set('Invalid username or password.');
          } else if (err?.status === 0) {
            this.error.set('Network/CORS issue. See console.');
          } else {
            this.error.set(`Login failed (${err?.status ?? 'unknown error'}).`);
          }
        }
      });
  }
}
