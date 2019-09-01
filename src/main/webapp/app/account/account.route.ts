import { Routes } from '@angular/router';

import {
  activateRoute,
  passwordRoute,
  passwordResetFinishRoute,
  passwordResetInitRoute,
  registerRoute,
  settingsRoute,
  createRoute,
  addUserChildRoute,
  addTaskRoute,
  addPeriodicTaskRoute,
  userTaskRoute
} from './';

const ACCOUNT_ROUTES = [
  activateRoute,
  passwordRoute,
  passwordResetFinishRoute,
  passwordResetInitRoute,
  registerRoute,
  settingsRoute,
  createRoute,
  addUserChildRoute,
  addTaskRoute,
  addPeriodicTaskRoute,
  userTaskRoute
];

export const accountState: Routes = [
  {
    path: '',
    children: ACCOUNT_ROUTES
  }
];
