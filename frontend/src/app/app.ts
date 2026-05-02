import { Component, signal, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LanguageService } from './services/language-service';
import { AuthService } from './services/auth-service';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet],
    template: `
    <main>
        <header class="header">
            <div class="container">
                <div class="centered-text"><span style="font-family: sans-serif; font-size:  3rem">In a Box</span></div>
                <div class="right-aligned-text">
                    <button (click)="langService.setLanguage('en')">EN</button>
                    <button (click)="langService.setLanguage('fr')">FR</button>
                    @if (auth.isAuthenticated()) {
                      <button (click)="onLogout()">Logout</button>
                    }
                </div>
            </div>
        </header>
        <router-outlet></router-outlet>
    </main>
  `,
    styleUrl: './app.css'
})
export class App {
    protected readonly title = signal('In a Box');
    protected readonly langService = inject(LanguageService);
    protected readonly auth = inject(AuthService);
    private readonly router = inject(Router);

    constructor() {
        // Optional diagnostic to observe initial auth state on app load
        console.log('[DEBUG_LOG] App: initial isAuthenticated =', this.auth.isAuthenticated());
    }

    onLogout() {
        console.log('[DEBUG_LOG] App: logout clicked');
        this.auth.logout();
        console.log('[DEBUG_LOG] App: isAuthenticated after logout =', this.auth.isAuthenticated());
        this.router.navigate(["/login"]);
    }
}
