export interface DashboardOverview {
  totalApis: number;
  activeApis: number;
  deprecatedApis: number;
  activeMocks: number;
    totalUsers: number;
    pendingApis: number;
}

export interface ApiCategoryByHttpMethod {
    httpMethod: httpMethod;
    count: number
}

export interface ApiCategoryByStatus {
    active: number,
    inactive: number
}

export enum httpMethod {
    GET, POST, PUT, DELETE
}
export interface ChartData {
  label: string;
  value: number;
}

export interface TimelineData {
  date: string;
  count: number;
}
