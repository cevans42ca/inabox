import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Update your interface to match the real API response
export interface BoxDto {
  id: string;
  name: string;
  location: string;
  description?: string;
  phonetic?: string;
  itemCount?: number;
}

@Injectable({ providedIn: 'root' })
export class BoxService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/overview';

  getBoxes(): Observable<BoxDto[]> {
    return this.http.get<BoxDto[]>(this.apiUrl);
  }
}
