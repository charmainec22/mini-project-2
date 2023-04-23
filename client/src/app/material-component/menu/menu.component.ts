import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { ProductComponent } from '../dialog/product/product.component';
import { ConfirmationComponent } from '../dialog/confirmation/confirmation.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  displayedColumn : string[] = ['name','description', 'price'];
  dataSource:any;
  responseMessage:any;

  constructor(private productService: ProductService,
    private dialog: MatDialog,
    private snackBarService: SnackbarService,
    private router : Router) { }

  ngOnInit(): void {
    this.tableData()
  }

  tableData() {
    this.productService.getProducts().subscribe((response:any) => {
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


}
