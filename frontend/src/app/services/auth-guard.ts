import { Injectable, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth-service';

export const authGuard: CanActivateFn = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  const hasToken = !!auth.getAuthorizationHeader();
  if (hasToken) {
    // Optional diagnostic to verify guard path
    console.log('[DEBUG_LOG] auth-guard: token present, allowing navigation');
    return true;
  }
  console.log('[DEBUG_LOG] auth-guard: no token, redirecting to /login');
  router.navigate(['/login']);
  return false;
};
