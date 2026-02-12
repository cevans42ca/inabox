import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {
  // We use a signal so components can "react" to changes automatically
  // Making a Signal readonly doesn't mean the value can't change; it means the Signal instance itself cannot be
  // replaced with a new one.
  readonly currentLang = signal<'en' | 'fr'>(
    navigator.language.startsWith('fr') ? 'fr' : 'en'
  );

  private readonly translations = {
    en: { '=0': 'No items', '=1': '1 item', 'other': '# items' },
    fr: { '=0': 'Aucun article', '=1': '1 article', 'other': '# articles' }
  };

  getMapping() {
    return this.translations[this.currentLang()];
  }

  setLanguage(lang: 'en' | 'fr') {
    this.currentLang.set(lang);
  }
}
