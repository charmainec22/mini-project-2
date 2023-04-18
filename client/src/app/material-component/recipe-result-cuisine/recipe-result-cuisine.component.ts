import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Recipes } from 'src/app/models/Recipes.model';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-recipe-result-cuisine',
  templateUrl: './recipe-result-cuisine.component.html',
  styleUrls: ['./recipe-result-cuisine.component.scss']
})
export class RecipeResultCuisineComponent implements OnInit, OnDestroy {
  displayedColumns: string[] = ['imagePath', 'name', 'url'];
  name!: string;
  cuisine!:string;
  convertedResult!: string;
  routeSub$!: Subscription;
  recipes: Recipes[] = [];
  dataSource:any;
  responseMessage:any;

  constructor(private activatedRoute: ActivatedRoute, 
    private productSvc: ProductService,
    private dialog: MatDialog,
    private snackbarService:SnackbarService) { }

  ngOnInit(): void {
    
    this.routeSub$ = this.activatedRoute.queryParams.subscribe((params)=> {
      this.cuisine = params['cuisine'];
    })
    console.log("cuisine loaded is: " + this.cuisine);
    this.tableData();
  }

  ngOnDestroy(): void {
    this.routeSub$.unsubscribe();
    this.tableData();
    this.getRecipeByCuisine();
  }

  tableData() {
    this.productSvc.getRecipeByCuisine(this.cuisine).subscribe((response) => {
      console.log(response);
      // this.convertedResult = JSON.stringify(response);
      // console.log("converted is " + this.convertedResult);
      this.dataSource = new MatTableDataSource(response);
      console.log(this.dataSource);
    }, (error:any) => {
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  getRecipeByCuisine() {
    this.productSvc.getRecipeByCuisine(this.cuisine).subscribe((response) => {
      //console.log(response);
      // this.convertedResult = JSON.stringify(response);
      // console.log("converted is " + this.convertedResult);
      this.recipes = response;
      //console.log(this.dataSource);
    }, (error:any) => {
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

}
