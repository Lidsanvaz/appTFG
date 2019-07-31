import { Route } from '@angular/router';

import { FamilyComponent } from './family.component';

export const FAMILY_ROUTE: Route = {
  path: 'families',
  component: FamilyComponent,
  data: {
    users: [],
    pageTitle: 'family.title'
  }
};
