/* import { Component } from '@angular/core';

@Component({
  selector: 'jhi-footer',
  templateUrl: './footer.component.html'
})
export class FooterComponent {} */
import { Component, OnInit } from '@angular/core';
import { Task } from 'app/core/task/task.model.ts';
import { startOfDay, endOfDay } from 'date-fns';

@Component({
  selector: 'jhi-footer',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  events: Task[] = [
    {
      nameTask: 'Conference',
      startDate: new Date(startOfDay(new Date())),
      endDate: new Date(endOfDay(new Date(2020, 2, 9))),
      color: 'info'
    },
    {
      nameTask: 'Meeting',
      startDate: new Date(startOfDay(new Date())),
      endDate: new Date(endOfDay(new Date(2020, 2, 9))),
      color: 'success'
    }
  ];

  generateUid() {
    const uid = Math.random()
      .toString(36)
      .substr(2, 9);
    return `mdb-calendar-event-${uid}`;
  }

  onEventEdit(event: Task) {
    const oldEvent = this.events.findIndex(test => test.nameTask === event.nameTask);
    this.events[oldEvent] = event;
    this.events = [...this.events];
  }

  onEventAdd(event: Task) {
    event.nameTask = this.generateUid();
    this.events = [...this.events, event];
  }

  ngOnInit() {}
}
