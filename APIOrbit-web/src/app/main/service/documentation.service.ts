import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DOCS } from '../interfaces/documentation.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DocumentationService {

  constructor(private _http: HttpClient) { }

  public getDocById(projectId : string): Observable<DOCS>{
    return this._http.get<DOCS>(`${environment.apiUrl}/docs/${projectId}`)
  }
  
  public updatedDocById(apiId: string, body : DOCS): Observable<DOCS>{
    return this._http.put<DOCS>(`${environment.apiUrl}/docs/${apiId}`, body)
  }
}
