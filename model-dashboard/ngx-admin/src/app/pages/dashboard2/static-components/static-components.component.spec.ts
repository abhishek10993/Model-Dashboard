import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaticComponentsComponent } from './static-components.component';

describe('StaticComponentsComponent', () => {
  let component: StaticComponentsComponent;
  let fixture: ComponentFixture<StaticComponentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaticComponentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaticComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
