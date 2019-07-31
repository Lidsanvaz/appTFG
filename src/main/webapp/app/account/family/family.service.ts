import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { Family } from './family.model';

@Injectable({ providedIn: 'root' })
export class FamilyService {
  public resourceUrl = SERVER_API_URL + 'api/families';

  constructor(private http: HttpClient) {}

  create(family: Family): Observable<HttpResponse<Family>> {
    return this.http.post<Family>(this.resourceUrl, family, { observe: 'response' });
  }

  update(family: Family): Observable<HttpResponse<Family>> {
    return this.http.put<Family>(this.resourceUrl, family, { observe: 'response' });
  }

  find(login: string): Observable<HttpResponse<Family>> {
    return this.http.get<Family>(`${this.resourceUrl}/${login}`, { observe: 'response' });
  }

  query(req?: any): Observable<HttpResponse<Family[]>> {
    const options = createRequestOption(req);
    return this.http.get<Family[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(nameFamily: string): Observable<HttpResponse<any>> {
    return this.http.delete(`${this.resourceUrl}/${nameFamily}`, { observe: 'response' });
  }

  users(): Observable<string[]> {
    return this.http.get<string[]>(SERVER_API_URL + 'api/families/users');
  }
  save(account: any): Observable<any> {
    return this.http.post(SERVER_API_URL + 'api/families', account);
  }
}
