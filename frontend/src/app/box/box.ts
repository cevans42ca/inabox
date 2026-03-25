import { Component, Input, inject } from '@angular/core';
import { I18nPluralPipe } from '@angular/common';
import { LanguageService } from '../services/language-service';
import { BoxDto } from "../box-dto"

@Component({
  selector: 'app-box',
  imports: [I18nPluralPipe],
  template: `
      <div class="bankers-box">
        <div class="box-lid"></div>
        <div class="box-body">
          <div class="label-area">
            <div class="box-name">{{ boxDto.name }}</div>
            <div class="dynamic-text">
              {{ boxDto.itemCount | i18nPlural: langService.getMapping() }}<br>
              {{ boxDto.location }}
            </div>
          </div>
        </div>
      </div>
  `,
  styleUrl: './box.css',
})

export class Box {
  @Input() boxDto!:BoxDto
  protected readonly langService = inject(LanguageService);
}
