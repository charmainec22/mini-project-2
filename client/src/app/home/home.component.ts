import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { SignupComponent } from '../signup/signup.component';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';
import { MatTableDataSource } from '@angular/material/table';
import { GlobalConstants } from '../shared/global-constants';
import { SnackbarService } from '../services/snackbar.service';
import { ManageProductComponent } from '../material-component/manage-product/manage-product.component';
import { MenuComponent } from '../material-component/menu/menu.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  dataSource:any;
  displayedColumn : string[] = ['name','description', 'price'];
  responseMessage:any;

  constructor(private dialog: MatDialog,
              private userService: UserService,
              private router: Router,
              private productService: ProductService,
              private snackBarService: SnackbarService) { }

  ngOnInit(): void {
    // this.userService.checkToken().subscribe((response:any) => {
    //   this.router.navigate(['cafe/dashboard']);
    // },(error:any) => {
    //   console.log(error);
    // })
    //this.tableData();
  }

  
  handleSignupAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(SignupComponent,dialogConfig);
  }

  handleForgotPasswordAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(ForgotPasswordComponent,dialogConfig);
  }

  handleLoginAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(LoginComponent,dialogConfig);
  }

  handleProductAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(MenuComponent,dialogConfig);
  }

  
}
