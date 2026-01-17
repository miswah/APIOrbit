import { ActivityLogsModel } from '@/main/interfaces/activity-logs.model';
import { ActivityLogsService } from '@/main/service/activity-logs.service';
import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Table, TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { ToolbarModule } from 'primeng/toolbar';

@Component({
  selector: 'app-activity-logs',
  imports: [TableModule, InputIconModule, IconFieldModule, SelectModule, FormsModule, CommonModule, TagModule, InputTextModule, ButtonModule, DialogModule, ToolbarModule],
  templateUrl: './activity-logs.component.html',
  styleUrl: './activity-logs.component.css'
})
export class ActivityLogsComponent implements OnInit {
  public activityLogs: ActivityLogsModel[] = [];
  loading: boolean = true;
  
  @ViewChild('filter') filter!: ElementRef;
  @ViewChild('dt') dt!: Table;
  
  constructor(private service: ActivityLogsService) { }


  ngOnInit(): void {
    this.service.getActivityLogs().subscribe((data: ActivityLogsModel[]) => {
      this.activityLogs = data;
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
          case 'GET':
          case 'SUCCESS':
                return 'success';
            case 'DELETE':
                return 'warn';

          case 'POST':
          case 'FAILED':
            return 'danger';
          case 'PUT':
            return 'secondary';
          
          case 'viewer':
            return 'contrast';

            default:
                return 'info';
        }
   }
  
}


