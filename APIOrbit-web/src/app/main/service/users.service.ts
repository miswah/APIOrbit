import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserModel } from '../interfaces/user.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private _http: HttpClient) {
    
  }

  public listAllUsers(): Observable<UserModel[]> {
    return this._http.get<UserModel[]>(`${environment.apiUrl}/user`);
  }

  public approveUser(id: string): Observable<UserModel> {
    return this._http.get<UserModel>(`${environment.apiUrl}/user/approve/${id}`);
  }

   public disableUser(id: string): Observable<UserModel> {
     return this._http.put<UserModel>(`${environment.apiUrl}/user/disable/${id}`, {});
  }
  
}
