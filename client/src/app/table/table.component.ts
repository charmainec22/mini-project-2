import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ProductComponent } from '../material-component/dialog/product/product.component';
import { SnackbarService } from '../services/snackbar.service';
import { GlobalConstants } from '../shared/global-constants';
import { TableService } from '../services/table.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  onAddTable = new EventEmitter();
  tableForm:any = FormGroup;
  dialogAction: any = "Add";
  action:any = "Add";
  responseMessage:any;
  categorys:any = [];

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData:any,
  private formBuilder: FormBuilder,
  public dialogRef: MatDialogRef<TableComponent>,
  private snackBarService: SnackbarService,
  private tableService : TableService) { }

  ngOnInit(): void {
    this.tableForm = this.formBuilder.group({
      tableId: [null, [Validators.required]],
      pax: [null, [Validators.required]],
    });
  }

  add() {
    var formData = this.tableForm.value;
    var data = {
      tableId: formData.tableId,
      pax : formData.pax
    }

    this.tableService.add(data).subscribe((response:any)=> {
      this.dialogRef.close();
      this.onAddTable.emit();
      this.responseMessage = response.message;
      this.snackBarService.openSnackBar(this.responseMessage, "success");
    }, (error) => {
      this.dialogRef.close();
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

  handleSubmit() {
    
      this.add();
    
  } 

}
