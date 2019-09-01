import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MiappjhipsterSharedModule } from 'app/shared';

import {
  PasswordStrengthBarComponent,
  RegisterComponent,
  CreateComponent,
  AddUserChildComponent,
  AddTaskComponent,
  AddPeriodicTaskComponent,
  UserTaskComponent,
  EditUserTaskComponent,
  ActivateComponent,
  PasswordComponent,
  PasswordResetInitComponent,
  PasswordResetFinishComponent,
  SettingsComponent,
  accountState
} from './';

@NgModule({
  imports: [MiappjhipsterSharedModule, RouterModule.forChild(accountState)],
  declarations: [
    ActivateComponent,
    RegisterComponent,
    CreateComponent,
    AddUserChildComponent,
    AddTaskComponent,
    AddPeriodicTaskComponent,
    UserTaskComponent,
    EditUserTaskComponent,
    PasswordComponent,
    PasswordStrengthBarComponent,
    PasswordResetInitComponent,
    PasswordResetFinishComponent,
    SettingsComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MiappjhipsterAccountModule {}
