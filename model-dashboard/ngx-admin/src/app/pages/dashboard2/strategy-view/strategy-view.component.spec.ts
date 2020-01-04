import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyViewComponent } from './strategy-view.component';

describe('StrategyViewComponent', () => {
  let component: StrategyViewComponent;
  let fixture: ComponentFixture<StrategyViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StrategyViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
