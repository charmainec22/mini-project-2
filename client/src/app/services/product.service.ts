import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Recipes } from '../models/Recipes.model';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  url = environment.apiUrl;
  
  

  constructor(private httpClient:HttpClient) { }

  add (data:any) {
    return this.httpClient.post(this.url + "/product/add", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  update (data:any) {
    return this.httpClient.post(this.url + "/product/update", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  getProducts () {
    return this.httpClient.get(this.url + "/product/get");
  }

  updateStatus (data:any) {
    return this.httpClient.post(this.url + "/product/updateStatus", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  delete (id:any) {
    return this.httpClient.post(this.url + "/product/delete/"+id, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  getProductsByCategory(id:any) {
    return this.httpClient.get(this.url + "/product/getByCategory/" + id);
  }

  getById(id:any) {
    return this.httpClient.get(this.url + "/product/getById/" + id);
  }

  getRecipe (name: string) {
    const params = new HttpParams()
    .set('query', name)
    console.log("query name is: " + name);
    console.log("param is" + params);
    console.log("URL generated is: " + this.url + "/product/getRecipe?" + params)
  //return lastValueFrom
  
    return this.httpClient.get<Recipes[]>(this.url + "/product/getRecipe?" + params);

  }

  getRecipeByCuisine (cuisine: string) {
    const params = new HttpParams()
    .set('cuisine', cuisine)
    console.log("cuisine name is: " + cuisine);
    console.log("param is" + params);
    console.log("URL generated is: " + this.url + "/product/getRecipeByCuisine?" + params)
  //return lastValueFrom
  
    return this.httpClient.get<Recipes[]>(this.url + "/product/getRecipeByCuisine?" + params);

  }
}
