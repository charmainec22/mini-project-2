import { Injectable } from "@angular/core";

export interface Menu {
    state: string;
    name: string;
    type:string;
    icon:string;
    role:string;
}

const MENUITEMS = [
    {state: 'dashboard', name: 'Dashboard', type: 'link', icon: 'dashboard', role:'admin'},
    {state: 'table', name: 'Manage Tables', type: 'link', icon: 'table', role:'admin'},
    {state: 'order', name: 'Manage Orders', type: 'link', icon: 'shopping_cart', role:''},
    {state: 'menu', name: 'Menu', type: 'link', icon: 'book', role:''},
    {state: 'category', name: 'Manage categories', type: 'link', icon: 'category', role:'admin'},
    {state: 'product', name: 'Manage products', type: 'link', icon: 'inventory_2', role:'admin'},
    {state: 'bill', name: 'View Bills', type: 'link', icon: 'backup_table', role:''},
    {state: 'user', name: 'Manage Users', type: 'link', icon: 'people', role:'admin'},
    {state: 'recipe', name: 'Find Recipes', type: 'link', icon: 'book', role:'admin'},
    
    
]

@Injectable()
export class MenuItems{
    getMenuitem():Menu[] {
        return MENUITEMS;
    }
}