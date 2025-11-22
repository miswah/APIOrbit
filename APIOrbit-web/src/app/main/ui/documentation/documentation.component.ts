import { APIModel } from '@/main/interfaces/api.model';
import { DOCS } from '@/main/interfaces/documentation.model';
import { ApiService } from '@/main/service/api.service';
import { DocumentationService } from '@/main/service/documentation.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';

import { EditorModule, EditorTextChangeEvent } from 'primeng/editor';
import { MessageModule } from 'primeng/message';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-documentation',
  imports: [EditorModule, FormsModule, CommonModule, MessageModule, CardModule, ButtonModule, BadgeModule, TagModule, DialogModule],
  templateUrl: './documentation.component.html',
  styleUrl: './documentation.component.css'
})
export class DocumentationComponent implements OnInit {

  doc: DOCS = {
    projectId: '',
    id: '',
    text: ''
  };
  apis: APIModel[] = [];
  docDialog: boolean = false;
  apiId: string = "";
  
  constructor(private apiService: ApiService, private docsService : DocumentationService, private _toastrService: ToastrService) { }

  ngOnInit(): void {
    this.apiService.getAllApi().subscribe((apis: APIModel[]) => {
      this.apis = apis;
    })
  }
  

  onTextChange(event: EditorTextChangeEvent) {
    // The 'text' property of the event object contains the plain text
    // console.log(event.htmlValue);
    this.doc.text = event.htmlValue;
  }

  openDialog(api: APIModel): void {
    this.docDialog = true;
    this.apiId = api.id;
    this.docsService.getDocById(api.id).subscribe((doc: DOCS) => {
      this.doc = doc;
    })
  }

  hideDialog(): void {
    this.docDialog = false;
    this.apiId = "";
  }

  savedoc(text: string) {
    this.doc.text = text;

    this.docsService.updatedDocById(this.apiId, this.doc).subscribe((doc: DOCS) => {
        this._toastrService.success(
                'You have successfully updated the documentation for this api',
              '',
                { toastClass: 'toast ngx-toastr', closeButton: true }
              );
              this.hideDialog();
    })
  }

}
