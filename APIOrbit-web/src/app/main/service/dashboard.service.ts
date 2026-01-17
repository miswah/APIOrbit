import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { DashboardOverview } from "../interfaces/dashboard.models";

@Injectable({ providedIn: 'root' })
export class DashboardService {

  getOverview() {
    return of<DashboardOverview>({
      totalApis: 42,
      activeApis: 36,
      deprecatedApis: 6,
      totalVersions: 124,
      activeMocks: 18,
      totalUsers: 9,
      pendingApis: 6
    });
  }

  getApisByMethod() {
    return of({
      labels: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH'],
      datasets: [{
        data: [18, 12, 6, 4, 2]
      }]
    });
  }

  getApisByStatus() {
    return of({
      labels: ['Active', 'Inactive'],
      datasets: [{
        data: [36, 6]
      }]
    });
  }

  getAuditByAction() {
    return of({
      labels: ['CREATE_API', 'UPDATE_API', 'DELETE_API', 'CREATE_MOCK'],
      datasets: [{
        data: [18, 24, 6, 11]
      }]
    });
  }

  getTopVersionedApis() {
    return of([
      { apiName: 'User Service', versions: 9 },
      { apiName: 'Payment Gateway', versions: 7 },
      { apiName: 'Order Service', versions: 6 }
    ]);
  }
}
