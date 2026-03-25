import { Component, signal, inject } from '@angular/core';
import { OuterBox } from "./outer-box/outer-box";
import { BoxDto } from "./box-dto";
import { LanguageService } from './services/language-service';

@Component({
    selector: 'app-root',
    imports: [OuterBox],
    template: `
    <main>
        <header class="header">
            <div class="container">
                <div class="centered-text"><span style="font-family: sans-serif; font-size:  3rem">In a Box</span></div>
                <div class="right-aligned-text">
                    <button (click)="langService.setLanguage('en')">EN</button>
                    <button (click)="langService.setLanguage('fr')">FR</button>
                </div>
            </div>
        </header>
        <section class="content">
          <app-outer-box [boxList]="boxList"></app-outer-box>
        </section>
    </main>
  `,
    styleUrl: './app.css'
})
export class App {
    protected readonly title = signal('In a Box');
    protected readonly langService = inject(LanguageService);
    boxList: BoxDto[] = [
        {
            "id": 1,
            "name": "Miscellaneous 1",
            "itemCount": 42,
            "location": "Upside Down"
        },
        {
            "id": 2,
            "name": "Miscellaneous 2",
            "itemCount": 42,
            "location": "Upside Down"
        },
        {
            "id": 3,
            "name": "Miscellaneous 3",
            "itemCount": 42,
            "location": "Upside Down"
        },
        {
            "id": 4,
            "name": "Miscellaneous 4",
            "itemCount": 42,
            "location": "Upside Down"
        },
        {
            "id": 5,
            "name": "Miscellaneous 5",
            "itemCount": 42,
            "location": "Upside Down"
        }
    ];
}
