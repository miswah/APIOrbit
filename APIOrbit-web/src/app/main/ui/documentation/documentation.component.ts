import { APIModel } from '@/main/interfaces/api.model';
import { DOCS } from '@/main/interfaces/documentation.model';
import { ApiService } from '@/main/service/api.service';
import { DocumentationService } from '@/main/service/documentation.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';

import { EditorModule, EditorTextChangeEvent } from 'primeng/editor';
import { MessageModule } from 'primeng/message';
import { TagModule } from 'primeng/tag';
import { JsonEditorComponent, JsonEditorOptions } from 'ang-jsoneditor';

@Component({
  selector: 'app-documentation',
  imports: [EditorModule, FormsModule, CommonModule, MessageModule, CardModule, ButtonModule, BadgeModule, TagModule, DialogModule, JsonEditorComponent],
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
  
  public editorOptions: JsonEditorOptions;
  public data: any;
  // optional
  @ViewChild(JsonEditorComponent, { static: false }) editor: JsonEditorComponent | undefined;

  constructor(private apiService: ApiService, private docsService: DocumentationService, private _toastrService: ToastrService) { 
      this.editorOptions = new JsonEditorOptions()
   
    // Configure modes for the editor
    this.editorOptions.modes = ['code', 'text', 'tree', 'view'];

    this.editorOptions.mode = 'text'; 
  
    // Optional: Add a callback for changes (used for validation)
    // this.editorOptions.onChange = () => this.validateJson();

    this.data = {"products":[{"name":"car","product":[{"name":"honda","model":[{"id":"civic","name":"civic"},{"id":"accord","name":"accord"},{"id":"crv","name":"crv"},{"id":"pilot","name":"pilot"},{"id":"odyssey","name":"odyssey"}]}]}]}
  }

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

  getData(event: any) {
    console.log(event)
  }

}
