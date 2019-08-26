import { Component, OnInit, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/shared';
import { LoginModalService } from 'app/core';
import { AddUserChild } from './addUserChild.service';

@Component({
  selector: 'jhi-addUserChild',
  templateUrl: './addUserChild.component.html'
})
export class AddUserChildComponent implements OnInit {
  doNotMatch: string;
  error: string;
  errorEmailExists: string;
  errorUserExists: string;
  success: boolean;
  modalRef: NgbModalRef;

  addForm = this.fb.group({
    nameUserChild: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
    bornDate: [Validators.required]
  });

  constructor(
    private languageService: JhiLanguageService,
    private loginModalService: LoginModalService,
    private addUserChildService: AddUserChild,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.success = false;
  }

  addUserChild() {
    let registerAccount = {};
    const nameUserChild = this.addForm.get(['nameUserChild']).value;
    const bornDate = this.addForm.get(['bornDate']).value;

    registerAccount = { ...registerAccount, nameUserChild, bornDate };
    this.doNotMatch = null;
    this.error = null;
    this.errorUserExists = null;
    this.errorEmailExists = null;

    this.addUserChildService.save(registerAccount).subscribe(
      () => {
        this.success = true;
      },
      response => this.processError(response)
    );
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
}
