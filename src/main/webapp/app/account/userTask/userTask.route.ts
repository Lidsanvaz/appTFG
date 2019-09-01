import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';

import { AccountService, User, UserService } from 'app/core';
import { UserTaskComponent } from './userTask.component';
import { EditUserTaskComponent } from './editUserTask.component';

@Injectable({ providedIn: 'root' })
export class UserTaskResolve implements CanActivate {
  constructor(private accountService: AccountService) {}

  canActivate() {
    return this.accountService.identity().then(account => this.accountService.hasAnyAuthority(['ROLE_USER']));
  }
}

export const userTaskRoute: Routes = [
  {
    path: 'userTask',
    component: UserTaskComponent,

    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'register.title'
    }
  },
  {
    path: 'editUserTask',
    component: EditUserTaskComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'register.title'
    }
  }
];
