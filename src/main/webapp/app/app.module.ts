import './vendor.ts';

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { NgJhipsterModule } from 'ng-jhipster';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MdbFileUploadModule } from 'mdb-file-upload';
import { MdbCalendarModule } from 'mdb-calendar';
import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { MiappjhipsterSharedModule } from 'app/shared';
import { MiappjhipsterCoreModule } from 'app/core';
import { MiappjhipsterAppRoutingModule } from './app-routing.module';
import { MiappjhipsterHomeModule } from './home/home.module';
import { MiappjhipsterAccountModule } from './account/account.module';
import { MiappjhipsterEntityModule } from './entities/entity.module';
import * as moment from 'moment';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent, NavbarComponent, PageRibbonComponent, ActiveMenuDirective, ErrorComponent, AppComponent } from './layouts';

@NgModule({
  imports: [
    BrowserModule,
    NgxWebstorageModule.forRoot({ prefix: 'jhi', separator: '-' }),
    NgJhipsterModule.forRoot({
      // set below to true to make alerts look like toast
      alertAsToast: false,
      alertTimeout: 5000,
      i18nEnabled: true,
      defaultI18nLang: 'es'
    }),
    MiappjhipsterSharedModule.forRoot(),
    MDBBootstrapModule.forRoot(),
    MdbFileUploadModule,
    MdbCalendarModule,
    MiappjhipsterCoreModule,
    MiappjhipsterHomeModule,
    MiappjhipsterAccountModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MiappjhipsterEntityModule,
    MiappjhipsterAppRoutingModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, AppComponent],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlerInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NotificationInterceptor,
      multi: true
    }
  ],
  bootstrap: [JhiMainComponent]
})
export class MiappjhipsterAppModule {
  constructor(private dpConfig: NgbDatepickerConfig) {
    this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
  }
}
