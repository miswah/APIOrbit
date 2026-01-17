import { DashboardOverview } from '@/main/interfaces/dashboard.models';
import { DashboardService } from '@/main/service/dashboard.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ChartModule } from 'primeng/chart';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, ChartModule, CardModule, TableModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
 overview!: DashboardOverview;
  apisByMethod: any;
  apisByStatus: any;
  auditByAction: any;
  topApis: any[] = [];

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.getOverview().subscribe(res => this.overview = res);
    this.dashboardService.getApisByMethod().subscribe(res => this.apisByMethod = res);
    this.dashboardService.getApisByStatus().subscribe(res => this.apisByStatus = res);
    this.dashboardService.getAuditByAction().subscribe(res => this.auditByAction = res);
    this.dashboardService.getTopVersionedApis().subscribe(res => this.topApis = res);
  }
}
