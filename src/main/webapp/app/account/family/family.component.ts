import { Component, OnInit, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/shared';
import { LoginModalService } from 'app/core';
import { FamilyService } from 'app/account';
@Component({
  selector: 'jhi-family',
  templateUrl: './family.component.html'
})
export class FamilyComponent implements OnInit, AfterViewInit {
  doNotMatch: string;
  error: string;
  errorEmailExists: string;
  errorUserExists: string;
  success: boolean;
  modalRef: NgbModalRef;

  familyForm = this.fb.group({
    nameFamily: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50), Validators.pattern('^[_.@A-Za-z0-9-]*$')]],
    users: [[]]
  });

  constructor(
    private languageService: JhiLanguageService,
    private loginModalService: LoginModalService,
    private familyService: FamilyService,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.success = false;
  }

  ngAfterViewInit() {
    this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#login'), 'focus', []);
  }

  save() {
    let registerFamily = {};
    const nameFamily = this.familyForm.get(['nameFamily']).value;
    registerFamily = { ...registerFamily, nameFamily };
    this.languageService.getCurrent().then(langKey => {
      registerFamily = { ...registerFamily, langKey };
      this.familyService.save(registerFamily).subscribe(
        () => {
          this.success = true;
        },
        response => this.processError(response)
      );
    });
  }

  openLogin() {
    this.modalRef = this.loginModalService.open();
  }

  private processError(response: HttpErrorResponse) {
    this.success = null;
    if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
      this.errorUserExists = 'ERROR';
    } else if (response.status === 400 && response.error.type === EMAIL_ALREADY_USED_TYPE) {
      this.errorEmailExists = 'ERROR';
    } else {
      this.error = 'ERROR';
    }
  }

  private familyFromForm(): any {
    const family = {};
    return {
      ...family,
      nameFamily: this.familyForm.get('nameFamily').value,
      users: this.familyForm.get('users').value
    };
  }

  updateForm(family: any): void {
    this.familyForm.patchValue({
      nameFamily: family.nameFamily,
      users: family.users
    });
  }
}
