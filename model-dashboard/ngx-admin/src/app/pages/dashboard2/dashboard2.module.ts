import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThemeModule } from '../../@theme/theme.module';
import { Dashboard2Component } from './dashboard2.component';
import { StrategyViewComponent } from './strategy-view/strategy-view.component';
import { SearchComponent } from './search/search.component';
import { StaticComponentsComponent } from './static-components/static-components.component';

@NgModule({
    declarations: [Dashboard2Component, StrategyViewComponent, SearchComponent, StaticComponentsComponent,],
  imports: [
      CommonModule,
      ThemeModule,
  ]
})
export class Dashboard2Module { }
