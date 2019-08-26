import { Component, OnInit, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/shared';
import { LoginModalService, TaskService } from 'app/core';
import { AddTask } from './addTask.service';

@Component({
  selector: 'jhi-addTask',
  templateUrl: './addTask.component.html'
})
export class AddTaskComponent implements OnInit {
  doNotMatch: string;
  error: string;
  errorEmailExists: string;
  errorUserExists: string;
  success: boolean;
  modalRef: NgbModalRef;
  userChilds: any[];

  addForm = this.fb.group({
    nameTask: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
    typeTask: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
    description: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    startDate: [],
    endDate: [],
    userChilds: [[]]
  });

  constructor(
    private languageService: JhiLanguageService,
    private loginModalService: LoginModalService,
    private addTaskService: AddTask,
    private taskService: TaskService,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.success = false;
    this.userChilds = [];
    this.taskService.userChilds().subscribe(userChilds => {
      this.userChilds = userChilds;
    });
  }

  addTask() {
    let registerAccount = {};
    const nameTask = this.addForm.get(['nameTask']).value;
    const typeTask = this.addForm.get(['typeTask']).value;
    const description = this.addForm.get(['description']).value;
    const startDate = this.addForm.get(['startDate']).value;
    const endDate = this.addForm.get(['endDate']).value;
    const userChilds = this.addForm.get(['userChilds']).value;

    registerAccount = { ...registerAccount, nameTask, typeTask, description, startDate, endDate, userChilds };
    this.doNotMatch = null;
    this.error = null;
    this.errorUserExists = null;
    this.errorEmailExists = null;

    this.addTaskService.save(registerAccount).subscribe(
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
