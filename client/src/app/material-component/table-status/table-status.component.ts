import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { ProductComponent } from '../dialog/product/product.component';
import { TableComponent } from 'src/app/table/table.component';
import { TableService } from 'src/app/services/table.service';
import { MatTableDataSource } from '@angular/material/table';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { ConfirmationComponent } from '../dialog/confirmation/confirmation.component';

@Component({
  selector: 'app-table-status',
  templateUrl: './table-status.component.html',
  styleUrls: ['./table-status.component.scss']
})
export class TableStatusComponent implements OnInit {

  displayedColumn : string[] = ['tableId', 'pax', 'edit'];
  dataSource:any;
  responseMessage:any;

  constructor(
    private dialog: MatDialog,
    private snackBarService: SnackbarService,
    private router : Router,
    private tableService : TableService) { }

  ngOnInit(): void {
    this.tableData()
  }

  handleAddAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      action : 'Add'
    };
    dialogConfig.width = "850px";
    const dialogRef = this.dialog.open(TableComponent, dialogConfig);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
    const sub = dialogRef.componentInstance.onAddTable.subscribe((response) => {
      this.tableData();
    })

  }

  tableData() {
    this.tableService.get().subscribe((response:any) => {
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

  handleDeleteAction(values:any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      message: 'delete ' + 'table '  + values.tableId ,
      confirmation:true
    }
    const dialogRef = this.dialog.open(ConfirmationComponent, dialogConfig);
    const sub = dialogRef.componentInstance.onEmitStatusChange.subscribe((response) => {
      this.deleteTable(values.id);
      dialogRef.close();
    })
  }

  deleteTable(id:any) {
    this.tableService.deleteTable(id).subscribe((response:any) => {
      this.tableData();
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

  onChange(status:any, id:any) {
    var data = {
      status: status.toString(),
      id : id
    }

    this.tableService.update(data).subscribe((response:any) => {
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

  handleOrderAction() {
    this.router.navigate(['/cafe/order']);
  }

}
