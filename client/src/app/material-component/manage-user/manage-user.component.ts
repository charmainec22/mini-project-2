import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { UserService } from 'src/app/services/user.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddadminComponent } from 'src/app/addadmin/addadmin.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-user',
  templateUrl: './manage-user.component.html',
  styleUrls: ['./manage-user.component.scss']
})
export class ManageUserComponent implements OnInit {

  displayedColumn : string[] = ['name', 'email', 'contactNumber', 'status'];
  dataSource:any;
  responseMessage:any;

  constructor(private userService: UserService,
    private snackBarService: SnackbarService,
    private dialog: MatDialog,
    private router : Router) { }

  ngOnInit(): void {
    this.tableData()
  }

  tableData() {
    this.userService.getUsers().subscribe((response:any) => {
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

  applyFilter(event:Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onChange(status:any, id:any) {
    var data = {
      status: status.toString(),
      id:id
    }

    this.userService.update(data).subscribe((response:any) => {
      this.responseMessage = response?.message;
      this.snackBarService.openSnackBar(this.responseMessage, "success");
    }, (error:any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackBarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  handleAddAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(AddadminComponent,dialogConfig);
    
  }
}
