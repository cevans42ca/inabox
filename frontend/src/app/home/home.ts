import { Component } from '@angular/core';
import { OuterBox } from "../outer-box/outer-box";
import { BoxDto } from "../box-dto";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [OuterBox],
  template: `
    <section class="content">
      <app-outer-box [boxList]="boxList"></app-outer-box>
    </section>
  `
})
export class HomeComponent {
  boxList: BoxDto[] = [
    { id: 1 as any, name: 'Miscellaneous 1', itemCount: 42, location: 'Upside Down' },
    { id: 2 as any, name: 'Miscellaneous 2', itemCount: 42, location: 'Upside Down' },
    { id: 3 as any, name: 'Miscellaneous 3', itemCount: 42, location: 'Upside Down' },
    { id: 4 as any, name: 'Miscellaneous 4', itemCount: 42, location: 'Upside Down' },
    { id: 5 as any, name: 'Miscellaneous 5', itemCount: 42, location: 'Upside Down' },
  ];
}
