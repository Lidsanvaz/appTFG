import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ITEMS_PER_PAGE } from 'app/shared';
import { AccountService, TaskService, Task } from 'app/core';

@Component({
  selector: 'jhi-userTask',
  templateUrl: './userTask.component.html'
})
export class UserTaskComponent implements OnInit, OnDestroy {
  currentAccount: any;
  tasks: Task[];
  error: any;
  success: any;
  routeData: any;
  links: any;
  totalItems: any;
  previousPage: any;
  /*  reverse: any; */
  page: any;
  /*   predicate: any;
/*  */
  /*   itemsPerPage: any;
   */ constructor(
    private taskService: TaskService,
    private alertService: JhiAlertService,
    private accountService: AccountService,
    private parseLinks: JhiParseLinks,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private eventManager: JhiEventManager,
    private modalService: NgbModal
  ) {
    /*  this.itemsPerPage = ITEMS_PER_PAGE; */
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data['pagingParams'].page;
      this.previousPage = data['pagingParams'].page;
      /*     this.reverse = data['pagingParams'].ascending;
      this.predicate = data['pagingParams'].predicate;  */
    });
  }

  ngOnInit() {
    this.accountService.identity().then(account => {
      this.currentAccount = account;
      this.loadAll();
      this.registerChangeInUsers();
    });
  }

  ngOnDestroy() {
    this.routeData.unsubscribe();
  }

  registerChangeInUsers() {
    this.eventManager.subscribe('userListModification', response => this.loadAll());
  }

  loadAll() {
    this.taskService.query().subscribe((res: HttpResponse<Task[]>) => this.onSuccess(res.body));
  }

  trackIdentity(index, item: Task) {
    return item.nameTask;
  }

  /*   sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  } */

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/userTask'], {});
    this.loadAll();
  }

  private onSuccess(data) {
    /*     this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = headers.get('X-Total-Count'); */
    this.tasks = data;
  }

  /*    private onError(error) {
    this.alertService.error(error.error, error.message, null);
  }  */
}
