import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FullComponent } from './layouts/full/full.component';
import { RouteGuardService } from './services/route-guard.service';
import { RecipeResultComponent } from './material-component/recipe-result/recipe-result.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'cafe',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: '/cafe/dashboard',
        pathMatch: 'full',
      },
      {
        path: '',
        loadChildren:
          () => import('./material-component/material.module').then(m => m.MaterialComponentsModule),
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin', 'user']
        }
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin', 'user']
        }
      },
      {
        path : 'recipe-result',
        redirectTo: '/cafe/recipe-result',
      },
      {
        path : 'recipe-result-cuisine',
        redirectTo: '/cafe/recipe-result-cuisine',
      }
    ]
  },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
