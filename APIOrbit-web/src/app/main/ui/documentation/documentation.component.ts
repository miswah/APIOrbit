import { APIModel, MockApi } from '@/main/interfaces/api.model';
import { DOCS } from '@/main/interfaces/documentation.model';
import { ApiService } from '@/main/service/api.service';
import { DocumentationService } from '@/main/service/documentation.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
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
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Toolbar } from 'primeng/toolbar';


interface HTTPMETHOD {
  label: string;
  value: string;
}
  
@Component({
  selector: 'app-documentation',
  imports: [EditorModule, FormsModule, CommonModule, MessageModule, CardModule, ButtonModule, BadgeModule, TagModule, DialogModule, JsonEditorComponent, InputTextModule, SelectModule, Toolbar],
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
  mockDialog: boolean = false;
  apiId: string = "";
  selectedApi: APIModel = {} as APIModel;
  mockResponse: any;
  selectedMockApi: MockApi = {} as MockApi;
  
  public editorOptions: JsonEditorOptions;
  public responseEditorOptions: JsonEditorOptions;
  public data: any;
  HttpMethods: HTTPMETHOD[] = [{ label: 'GET', value: 'GET' }, { label: 'POST', value: 'POST' }, { label: 'PUT', value: 'PUT' }, { label: 'DELETE', value: 'DELETE' }];
  // optional
  // @ViewChild(JsonEditorComponent, { static: false }) editor: JsonEditorComponent | undefined;
  @ViewChildren(JsonEditorComponent) editors!: QueryList<JsonEditorComponent>;

  constructor(private apiService: ApiService, private docsService: DocumentationService, private _toastrService: ToastrService) { 
    this.editorOptions = new JsonEditorOptions();
    this.responseEditorOptions = new JsonEditorOptions();
    this.responseEditorOptions.expandAll = true;
   
    // Configure modes for the editor
    this.editorOptions.modes = ['code', 'text', 'tree', 'view'];
    this.responseEditorOptions.modes = ['view'];

    this.editorOptions.mode = 'text'; 
    this.responseEditorOptions.mode = 'view';
  
    // Optional: Add a callback for changes (used for validation)
    // this.editorOptions.onChange = () => this.validateJson();
    // this.mockResponse = { "products":[{"name":"car","product":[{"name":"honda","model":[{"id":"civic","name":"civic"},{"id":"accord","name":"accord"},{"id":"crv","name":"crv"},{"id":"pilot","name":"pilot"},{"id":"odyssey","name":"odyssey"}]}]}]}
    // this.data = {"products":[{"name":"car","product":[{"name":"honda","model":[{"id":"civic","name":"civic"},{"id":"accord","name":"accord"},{"id":"crv","name":"crv"},{"id":"pilot","name":"pilot"},{"id":"odyssey","name":"odyssey"}]}]}]}
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

  openMockDialog(api: APIModel) {
    this.mockDialog = true;
    this.selectedApi = api;

    this.apiService.getMockApi(api.id).subscribe((res) => {
      this.selectedMockApi.id = res.id;
      this.selectedMockApi.delay = res.delay;
      this.selectedMockApi.schemaRequest = JSON.parse(res.schemaRequest);
      this.selectedMockApi.schemaResponse = JSON.parse(res.schemaResponse);
    })
  }

  hideMockDialog() {
    this.mockDialog = false;
    this.selectedApi = {} as APIModel;
  }

  savemock(mockApi: MockApi) {
    let isValid = true;
    this.editors.forEach((editor, index) => {
      if (!editor.isValidJson()) {
        isValid = false;
        }
    })

    if (!isValid) {
      alert("invalid json");
    }
    
    mockApi.schemaRequest = JSON.stringify(mockApi.schemaRequest);
    mockApi.schemaResponse = JSON.stringify(mockApi.schemaResponse);

    this.apiService.updateMockApi(mockApi).subscribe((res) => {
      this.hideMockDialog();
    })
  }

}
