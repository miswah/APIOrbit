import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { APIModel } from '../interfaces/api.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private _http: HttpClient) { }

  public getAllApi(): Observable<APIModel[]>{
    return this._http.get<APIModel[]>(`${environment.apiUrl}/base/list`);
  }

  public approveApi(id: string): Observable<APIModel> {
    return this._http.put<APIModel>(`${environment.apiUrl}/base/approve/${id}`, {});
  }
  
  public disableApi(id: string): Observable<APIModel> {
    return this._http.put<APIModel>(`${environment.apiUrl}/base/disable/${id}`, {});
  }

  public updatedApi(id: string, body : APIModel): Observable<APIModel> {
    return this._http.put<APIModel>(`${environment.apiUrl}/base/${id}`, body);
  }

  public createApi(body: APIModel): Observable<APIModel> {
    return this._http.post<APIModel>(`${environment.apiUrl}/base`, body);
  }
}
