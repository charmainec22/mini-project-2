import { Routes } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { ManageCategoryComponent } from './manage-category/manage-category.component';
import { RouteGuardService } from '../services/route-guard.service';
import { ManageProductComponent } from './manage-product/manage-product.component';
import { ManageOrderComponent } from './manage-order/manage-order.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { ManageUserComponent } from './manage-user/manage-user.component';
import { RecipeComponent } from './recipe/recipe.component';
import { RecipeResultComponent } from './recipe-result/recipe-result.component';
import { RecipeResultCuisineComponent } from './recipe-result-cuisine/recipe-result-cuisine.component';
import { MenuComponent } from './menu/menu.component';
import { TableStatusComponent } from './table-status/table-status.component';


export const MaterialRoutes: Routes = [
    {
        path: 'category',
        component: ManageCategoryComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
      {
        path: 'product',
        component: ManageProductComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
      {
        path: 'order',
        component: ManageOrderComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin','user']
        }
      },
      {
        path: 'bill',
        component: ViewBillComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin','user']
        }
      },
      {
        path: 'user',
        component: ManageUserComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
      {
        path: 'recipe',
        component: RecipeComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
      {
        path: 'recipe-result',
        component: RecipeResultComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
      {
        path: 'recipe-result-cuisine',
        component: RecipeResultCuisineComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
      {
        path: 'menu',
        component: MenuComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin','user']
        }
      },
      {
        path: 'table',
        component: TableStatusComponent,
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      }

];
