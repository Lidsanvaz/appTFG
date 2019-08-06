import { Route, CanActivate } from '@angular/router';
import { UserRouteAccessService, AccountService } from 'app/core';
import { Injectable } from '@angular/core';

import { AddUserChildComponent } from './addUserChild.component';

@Injectable({ providedIn: 'root' })
export class UserChildResolve implements CanActivate {
  constructor(private accountService: AccountService) {}

  canActivate() {
    return this.accountService.identity().then(account => this.accountService.hasAnyAuthority(['ROLE_USER']));
  }
}

export const addUserChildRoute: Route = {
  path: 'addUserChild',
  component: AddUserChildComponent,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'register.title'
  }
};
