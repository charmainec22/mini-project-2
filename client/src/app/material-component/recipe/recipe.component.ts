import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.scss']
})


export class RecipeComponent implements OnInit {

  recipeForm: any = FormGroup;
  recipeFormCuisine: any = FormGroup;
  dataSource: any;
  responseMessage: any;
  name!: string;
  cuisine!:string;

  constructor(private formBuilder: FormBuilder, private router: Router,
    private dialog: MatDialog,
    private snackBarService: SnackbarService,
    private productService: ProductService) { }

  ngOnInit(): void {
    this.recipeForm = this.createForm();
    this.recipeFormCuisine = this.createFormCuisine();
    //this.tableData();
  }

  tableData() {
    this.productService.getRecipe(this.name).subscribe((response:any) => {
      this.dataSource = new MatTableDataSource(response);
    }, (error:any) => {
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackBarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  createForm() : FormGroup {
    return this.formBuilder.group({
      name: this.formBuilder.control('', [Validators.required])
    });
  }

  createFormCuisine() : FormGroup {
    return this.formBuilder.group({
      cuisine: this.formBuilder.control('', [Validators.required])
    });
  }

  submitForm(): void {
    //on submit, route to characters?nameStartsWith=spider
    const name = this.recipeForm.get('name')?.value;
    //log.info("NAME IS:" + name);
    this.router.navigate(['/cafe/recipe-result'], {
      queryParams: {query: name},
    });
  }

  submitFormCuisine(): void {
    //on submit, route to characters?nameStartsWith=spider

    const cuisine = this.recipeFormCuisine.get('cuisine')?.value;
    console.log("cuisin gotten drop ddl is: " + cuisine);
    //log.info("NAME IS:" + name);
    this.router.navigate(['/cafe/recipe-result-cuisine'], {
      queryParams: {cuisine: cuisine},
    });
  }

  add() {
    var formData = this.recipeForm.value;
     this.dataSource.push({
      name: formData.name
     })
     this.dataSource = [...this.dataSource];
     this.snackBarService.openSnackBar(GlobalConstants.productAdded, "success");
  }

}
