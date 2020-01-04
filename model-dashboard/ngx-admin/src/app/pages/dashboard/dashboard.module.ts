import { NgModule } from '@angular/core';
import { NgxChartsModule } from '@swimlane/ngx-charts';



import { ThemeModule } from '../../@theme/theme.module';
import { DashboardComponent } from './dashboard.component';
import { LinegraphComponent } from './linegraph/linegraph.component';


const components=[
];

@NgModule({
  imports: [
      ThemeModule,
      NgxChartsModule,
  ],
  declarations: [
      DashboardComponent,
      components,
      LinegraphComponent,

  ],
})
export class DashboardModule { }
