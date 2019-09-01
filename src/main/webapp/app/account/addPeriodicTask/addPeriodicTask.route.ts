import { Route, CanActivate } from '@angular/router';
import { UserRouteAccessService, AccountService } from 'app/core';
import { Injectable } from '@angular/core';

import { AddPeriodicTaskComponent } from './addPeriodicTask.component';

@Injectable({ providedIn: 'root' })
export class PeriodicTaskResolve implements CanActivate {
  constructor(private accountService: AccountService) {}

  canActivate() {
    return this.accountService.identity().then(account => this.accountService.hasAnyAuthority(['ROLE_USER']));
  }
}

export const addPeriodicTaskRoute: Route = {
  path: 'addPeriodicTask',
  component: AddPeriodicTaskComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'addTask.title'
  }
};
