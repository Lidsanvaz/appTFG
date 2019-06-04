import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MiappjhipsterSharedLibsModule, MiappjhipsterSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [MiappjhipsterSharedLibsModule, MiappjhipsterSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [MiappjhipsterSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MiappjhipsterSharedModule {
  static forRoot() {
    return {
      ngModule: MiappjhipsterSharedModule
    };
  }
}
