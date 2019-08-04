import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { Family } from './family.model';

@Injectable({ providedIn: 'root' })
export class FamilyService {
  public resourceUrl = SERVER_API_URL + 'api/family';

  constructor(private http: HttpClient) {}

  users(): Observable<string[]> {
    return this.http.get<string[]>(SERVER_API_URL + 'api/family/users');
  }
}
