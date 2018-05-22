import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FoobarEnterComponent } from './foobar-enter/foobarenter.component';
import { FoobarStatsComponent } from './foobar-stats/foobarstats.component';

const routes: Routes = [
  { path: '', redirectTo: '/foobarenter', pathMatch: 'full' },
  { path: 'foobarenter', component: FoobarEnterComponent },
  { path: 'foobarstats', component: FoobarStatsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
