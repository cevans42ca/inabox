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
          <div class="bankers-box">
            <div class="box-lid"></div>
            <div class="box-body">
              <div class="label-area">
                <div class="box-name">Box Name</div>
                <div class="dynamic-text" contenteditable="true">
                  Contains 42 items
                </div>
              </div>
            </div>
          </div>
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
        }
    ];
}
