import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivityLogsModel } from '../interfaces/activity-logs.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ActivityLogsService {

  constructor(private _http: HttpClient) { }
  

  public getActivityLogs(): Observable<ActivityLogsModel[]> {
    return this._http.get<ActivityLogsModel[]>(`${environment.apiUrl}/activity`);
  }
}
