import { APIModel } from '@/main/interfaces/api.model';
import { ApiService } from '@/main/service/api.service';
import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Table, TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { ToolbarModule } from 'primeng/toolbar';

interface AuthTypes {
  value: string,
  label: string
}

@Component({
  selector: 'app-apis',
  imports: [TableModule, InputIconModule, IconFieldModule, SelectModule, FormsModule, CommonModule, TagModule, InputTextModule, ButtonModule, DialogModule, ToolbarModule],
  templateUrl: './apis.component.html',
  styleUrl: './apis.component.css'
})
export class ApisComponent implements OnInit{
  apis: APIModel[] = [];
  loading: boolean = true;
  statuses: any[] = [];
  apiDialog: boolean = false;
  selectedApi: APIModel = {} as APIModel;
  submitted: boolean = false;
  AuthTypes: AuthTypes[] = [{ label: 'JWT', value: 'JWT' }, { label: 'BASIC', value: 'BASIC' }, { label: 'OAUTH', value: 'OAUTH' }, { label: 'OAUTH2', value: 'OAUTH2' }];

  @ViewChild('filter') filter!: ElementRef;
  @ViewChild('dt') dt!: Table;
  
  constructor(private apiService: ApiService, private _toastrService: ToastrService) { }

  ngOnInit(): void {
    this.apiService.getAllApi().subscribe((data: APIModel[]) => {
      this.apis = data;
      this.apis.map((api: APIModel) => api.tagArray = api.tags?.split(","));
      this.loading = false;
    })
  }

   onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
  
   clear(table: Table) {
        table.clear();
        this.filter.nativeElement.value = '';
   }
  
   getSeverity(status: string) {
        switch (status) {
            case 'qualified':
            case 'instock':
            case 'INSTOCK':
            case 'DELIVERED':
            case 'delivered':
          case 'admin':
            case 'active':
                return 'success';

            case 'negotiation':
            case 'lowstock':
            case 'LOWSTOCK':
            case 'PENDING':
            case 'pending':
                return 'warn';

            case 'unqualified':
            case 'outofstock':
            case 'OUTOFSTOCK':
            case 'CANCELLED':
          case 'cancelled':
            case 'deactive':
            return 'danger';

          case 'editor':
            return 'secondary';
          
          case 'viewer':
            return 'contrast';

            default:
                return 'info';
        }
   }
  
  approveAPi(id : string) {
    this.apiService.approveApi(id).subscribe((api: APIModel) => {
       this._toastrService.success(
                      'You have successfully approved the API with the name of' +
                        api.name,
                      '👋 now you can use this api, ',
                      { toastClass: 'toast ngx-toastr', closeButton: true }
                    );
           this.updatedApiData(api);
    })
  }


  blockApi(id : string) {
    this.apiService.disableApi(id).subscribe((api: APIModel) => {
       this._toastrService.success(
                      'You have successfully disabled the API with the name of' +
                        api.name,
                      '👋 now you can no longer use this api, ',
                      { toastClass: 'toast ngx-toastr', closeButton: true }
                    );
            this.updatedApiData(api);
    })
  }

  openApiDetails(selectedApi : APIModel) : void {
    this.selectedApi = selectedApi;
    this.apiDialog = true;
  }

  saveapi(selectedApi: APIModel) {
    if (!selectedApi.id) {
      this.createApi(selectedApi);
      return;
    }

    this.apiService.updatedApi(selectedApi.id, selectedApi).subscribe((api: APIModel) => {
    api.tagArray = api.tags?.split(",");
       this._toastrService.success(
                      'You have successfully updated the API with the name of' +
                        api.name,
                     "",
                      { toastClass: 'toast ngx-toastr', closeButton: true }
                    );
      this.updatedApiData(api);
      this.hideDialog();
    })
  }

  hideDialog() {
    this.apiDialog = false;
    this.submitted = false;
  }

  updatedApiData(api : APIModel) {
    const index = this.apis.findIndex((e: APIModel) => e.id == api.id);
            this.apis[index] = api;
  }

  openNew() {
        this.selectedApi = {} as APIModel;
        this.submitted = false;
        this.apiDialog = true;
  }
  
  deleteSelectedProducts() { }
  
  exportCSV() {
      this.dt.exportCSV();
  }

  createApi(selectedApi: APIModel): void {
    selectedApi.tags = selectedApi.tagArray ? selectedApi.tagArray.join(",") : "";
    this.apiService.createApi(selectedApi).subscribe((api: APIModel) => {
        this._toastrService.success(
                      'You have successfully created the API with the name of' +
                        api.name,
                     "",
                      { toastClass: 'toast ngx-toastr', closeButton: true }
        );
      
      this.apis.push(api);
      this.hideDialog();
    })
  }

}
