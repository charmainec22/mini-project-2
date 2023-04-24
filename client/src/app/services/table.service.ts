import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TableService {

  url = environment.apiUrl;

  constructor(private httpClient:HttpClient) { 
    
  }

  add (data:any) {
    return this.httpClient.post(this.url + "/bill/add", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  get() {
    return this.httpClient.get(this.url + "/bill/get");
  }

  update(data:any) {
    return this.httpClient.post(this.url + "/bill/update", data, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    })
  }

  deleteTable(id:any){
    return this.httpClient.post(this.url + "/bill/deleteTable/"+ id, {
      headers: new HttpHeaders().set('Content-Type', "application/json")
    });
  }
}
