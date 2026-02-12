import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OuterBox } from './outer-box';

describe('OuterBox', () => {
  let component: OuterBox;
  let fixture: ComponentFixture<OuterBox>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OuterBox]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OuterBox);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
