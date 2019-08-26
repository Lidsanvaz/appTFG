import { Route, CanActivate } from '@angular/router';
import { UserRouteAccessService, AccountService } from 'app/core';
import { Injectable } from '@angular/core';

import { AddTaskComponent } from './addTask.component';

@Injectable({ providedIn: 'root' })
export class TaskResolve implements CanActivate {
  constructor(private accountService: AccountService) {}

  canActivate() {
    return this.accountService.identity().then(account => this.accountService.hasAnyAuthority(['ROLE_USER']));
  }
}

export const addTaskRoute: Route = {
  path: 'addTask',
  component: AddTaskComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'addTask.title'
  }
};
