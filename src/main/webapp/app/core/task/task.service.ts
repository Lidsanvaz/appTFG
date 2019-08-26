import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { Task } from './task.model';

@Injectable({ providedIn: 'root' })
export class TaskService {
  public resourceUrl = SERVER_API_URL + 'api/task';

  constructor(private http: HttpClient) {}

  userChilds(): Observable<string[]> {
    return this.http.get<string[]>(SERVER_API_URL + 'api/task/userChilds');
  }

  users(): Observable<string[]> {
    return this.http.get<string[]>(SERVER_API_URL + 'api/task/users');
  }
}
