import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { DashboardOverview } from '../interfaces/dashboard.models';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  /* ===============================
     OVERVIEW
  =============================== */
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

  /* ===============================
     API INVENTORY
  =============================== */

  // GET /api/dashboard/apis/by-method
  getApisByMethod() {
    return of({
      labels: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH'],
      datasets: [
        {
          data: [18, 12, 6, 4, 2]
        }
      ]
    });
  }

  // GET /api/dashboard/apis/by-status
  getApisByStatus() {
    return of({
      labels: ['Active', 'Inactive'],
      datasets: [
        {
          data: [36, 6]
        }
      ]
    });
  }

  // GET /api/dashboard/apis/by-tag
  getApisByTag() {
    return of({
      labels: ['Payment', 'Auth', 'Order', 'User'],
      datasets: [
        {
          label: 'APIs',
          data: [10, 8, 14, 6]
        }
      ]
    });
  }

  /* ===============================
     VERSIONING
  =============================== */

  // GET /api/dashboard/versions/timeline
  getVersionTimeline() {
    return of({
      labels: ['Jan', 'Feb', 'Mar', 'Apr'],
      datasets: [
        {
          label: 'Versions Created',
          data: [5, 12, 20, 32],
          fill: false
        }
      ]
    });
  }

  // GET /api/dashboard/apis/top-versioned
  getTopVersionedApis() {
    return of([
      { apiName: 'User Service', versions: 9 },
      { apiName: 'Payment Gateway', versions: 7 },
      { apiName: 'Order Service', versions: 6 },
      { apiName: 'Inventory Service', versions: 5 }
    ]);
  }

  /* ===============================
     MOCK APIs
  =============================== */

  // GET /api/dashboard/mocks/status
  getMockStatus() {
    return of({
      labels: ['Enabled', 'Disabled'],
      datasets: [
        {
          data: [18, 9]
        }
      ]
    });
  }

  // GET /api/dashboard/mocks/top-used
  getTopUsedMocks() {
    return of([
      {
        apiName: 'Order Service',
        scenario: 'Success Response',
        hits: 245
      },
      {
        apiName: 'Payment Gateway',
        scenario: 'Failure Simulation',
        hits: 190
      }
    ]);
  }

  /* ===============================
     AUDIT & ACTIVITY
  =============================== */

  // GET /api/dashboard/audit/by-action
  getAuditByAction() {
    return of({
      labels: ['CREATE_API', 'UPDATE_API', 'DELETE_API', 'CREATE_MOCK'],
      datasets: [
        {
          data: [18, 24, 6, 11]
        }
      ]
    });
  }

  // GET /api/dashboard/audit/timeline
  getAuditTimeline() {
    return of({
      labels: ['Day 1', 'Day 2', 'Day 3', 'Day 4'],
      datasets: [
        {
          label: 'Create',
          data: [3, 2, 4, 1]
        },
        {
          label: 'Update',
          data: [5, 7, 6, 8]
        },
        {
          label: 'Delete',
          data: [1, 0, 2, 1]
        }
      ]
    });
  }

  // GET /api/dashboard/audit/top-users
  getTopUsers() {
    return of([
      { username: 'admin', actionsPerformed: 45 },
      { username: 'developer1', actionsPerformed: 28 },
      { username: 'developer2', actionsPerformed: 19 }
    ]);
  }

  /* ===============================
     USERS & SECURITY
  =============================== */

  // GET /api/dashboard/users/by-role
  getUsersByRole() {
    return of({
      labels: ['ADMIN', 'DEVELOPER', 'VIEWER'],
      datasets: [
        {
          data: [2, 6, 1]
        }
      ]
    });
  }

  // GET /api/dashboard/security/validation-failures
  getValidationFailures() {
    return of({
      labels: ['Invalid JSON', 'Invalid URL Path'],
      datasets: [
        {
          label: 'Failures',
          data: [12, 7]
        }
      ]
    });
  }
}
