import { Component, Input } from '@angular/core';
import { Box } from "../box/box";
import { BoxDto } from "../box-dto"

@Component({
  selector: 'app-outer-box',
  imports: [Box],
  template: `
    <section>
      <form>
        <input type="text" placeholder="Filter by Name">
        <button class="primary" type="button">Search</button>
      </form>
    </section>
	<section class="results">
	  @for (boxDto of boxList; track boxDto.id) {
	    <app-box [boxDto]="boxDto"></app-box>
	  }
	</section>
  `,
  styleUrl: './outer-box.css',
})
export class OuterBox {
  @Input() boxList: BoxDto[] = []
}
