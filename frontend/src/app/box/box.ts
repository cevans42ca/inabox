import { Component, Input, inject } from '@angular/core';
import { I18nPluralPipe } from '@angular/common';
import { LanguageService } from '../services/language-service';
import { BoxDto } from "../box-dto"

@Component({
  selector: 'app-box',
  imports: [I18nPluralPipe],
  template: `
    <section class="box">
      <span class="box-name">{{ boxDto.name }}</span><br>
      <span class="box-item-count">{{ boxDto.itemCount | i18nPlural: langService.getMapping() }}</span><br>
      <span class="box-location">{{ boxDto.location }}</span>
    </section>
  `,
  styleUrl: './box.css',
})

export class Box {
  @Input() boxDto!:BoxDto
  protected readonly langService = inject(LanguageService);
}
