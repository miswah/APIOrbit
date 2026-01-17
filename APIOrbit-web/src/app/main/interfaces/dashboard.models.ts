export interface DashboardOverview {
  totalApis: number;
  activeApis: number;
  deprecatedApis: number;
  totalVersions: number;
  activeMocks: number;
  totalUsers: number;
}

export interface ChartData {
  label: string;
  value: number;
}

export interface TimelineData {
  date: string;
  count: number;
}
