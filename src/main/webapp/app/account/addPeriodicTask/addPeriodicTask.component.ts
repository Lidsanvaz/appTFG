import { Component, OnInit, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from 'app/shared';
import { LoginModalService, TaskService } from 'app/core';
import { AddPeriodicTask } from './addPeriodicTask.service';

@Component({
  selector: 'jhi-addPeriodicTask',
  templateUrl: './addPeriodicTask.component.html'
})
export class AddPeriodicTaskComponent implements OnInit {
  doNotMatch: string;
  error: string;
  errorEmailExists: string;
  errorUserExists: string;
  success: boolean;
  modalRef: NgbModalRef;
  typeTasks: any[];
  userChilds: any[];
  weekDays: any[];

  addForm = this.fb.group({
    nameTask: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
    description: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    startDate: [],
    endDate: [],
    typeTasks: [[]],
    userChilds: [[]],
    weekDays: [[]]
  });

  constructor(
    private languageService: JhiLanguageService,
    private loginModalService: LoginModalService,
    private addPeriodicTaskService: AddPeriodicTask,
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
    const description = this.addForm.get(['description']).value;
    const startDate = this.addForm.get(['startDate']).value;
    const endDate = this.addForm.get(['endDate']).value;
    const typeTasks = this.addForm.get(['typeTasks']).value;
    const userChilds = this.addForm.get(['userChilds']).value;
    const weekDays = this.addForm.get(['weekDays']).value;

    registerAccount = { ...registerAccount, nameTask, description, startDate, endDate, typeTasks, userChilds, weekDays };
    this.doNotMatch = null;
    this.error = null;
    this.errorUserExists = null;
    this.errorEmailExists = null;

    this.addPeriodicTaskService.save(registerAccount).subscribe(
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
