import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { PagesRoutingModule } from './pages-routing.module';
import { ThemeModule } from '../@theme/theme.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { Dashboard2Module } from './dashboard2/dashboard2.module';



const PAGES_COMPONENTS = [
  PagesComponent,
];

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
      DashboardModule,
      Dashboard2Module,
    MiscellaneousModule,
  ],
  declarations: [
    ...PAGES_COMPONENTS,

  ],
})
export class PagesModule {
}
