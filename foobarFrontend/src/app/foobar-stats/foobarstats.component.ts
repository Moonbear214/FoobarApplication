import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

import { FooBar } from '../foobar';

const httpOptions = {
  headers: new Headers({ 'Content-Type': 'application/json' })
};

@Component({
  selector: 'app-stats',
  templateUrl: './foobarstats.component.html'
})
export class FoobarStatsComponent implements OnInit {
  constructor(private http: Http) {
  }

  apiRoot = 'http://localhost:8080/api';
  foobars: FooBar[];
  loading = false;
  noResults = false;
  requestErr = false;

  // When the Page loads it tries to get all the data from the backend
  ngOnInit() {
    this.loading = true;
    this.doGet();
  }

  // Gets all foobars from backend.
  // If successfull: Displays it to the user.
  // else: Logs it to the console and displays a message to the user
  // ====================================================================================
  doGet(): void {
    const apiURL = `${this.apiRoot}/foobar`;
    this.http.get(apiURL)
      .toPromise()
      .then(res => { // Success
          this.foobars = res.json();
          this.loading = false;
          if (this.foobars.length === 0) {
            this.noResults = true;
          }
          console.log('Foobar list received');
        },
        msg => { // Error
          this.loading = false;
          this.requestErr = true;
          console.log(msg);
        }
      );
  }
  // ====================================================================================
}
