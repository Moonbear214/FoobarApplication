import { Component, OnInit } from '@angular/core';
import { Http, Headers } from '@angular/http';

import { FooBar } from '../foobar';

const httpOptions = {
  headers: new Headers({ 'Content-Type': 'application/json' })
};

@Component({
  selector: 'app-enter',
  templateUrl: './foobarenter.component.html'
})

export class FoobarEnterComponent implements OnInit {
  constructor(private http: Http) {
  }

  apiRoot = 'http://localhost:8080/api';
  foobar: FooBar;

  ngOnInit() {
    this.foobar = new FooBar();
  }

  // Is called when calculate button is clicked.
  // 1st: Resets all values of the default foobar object
  // 2nd: Checks if the given numbers are valid and tells user if not
  // 3rd: Calculates the foobar values using the calcFooBar function
  // 4th: Does a post to save the calculated foobar to the server
  // ====================================================================================
  btnCalcClick(): void {
    this.foobarReset();

    if (this.foobar.foo < 1 || this.foobar.bar < 1) {
      this.foobar.result = 'Please enter a valid number.';
    } else {
      for (let i = 1; i <= 42; i++) {
        this.calcFooBar(this.foobar.foo, this.foobar.bar, i);
      }

      this.doPost(this.foobar);
    }
  }
  // ====================================================================================

  // Resets the default foobar object
  // ====================================================================================
  foobarReset(): void {
    this.foobar.id = 0;
    this.foobar.fooCount = 0;
    this.foobar.barCount = 0;
    this.foobar.foobarCount = 0;
    this.foobar.result = '';
  }
  // ====================================================================================

  // Calculates the foobar object that will be saved to the server
  // ====================================================================================
  calcFooBar(foo, bar, i): void {
    if (i % foo === 0) {
      this.foobar.result += 'Foo,';
      this.foobar.fooCount++;
    }

    if (i % bar === 0) {
      this.foobar.result += 'Bar,';
      this.foobar.barCount++;
    }

    if (i % (foo * bar) === 0) {
      this.foobar.result += 'FooBar,';
      this.foobar.foobarCount++;
    }

    this.foobar.result += i;

    if (i !== 42) {
      this.foobar.result += ',';
    }
  }
  // ====================================================================================

  // Sends the calculated foobar object to the server
  // ====================================================================================
  doPost(foobar): void {
      const url = `${this.apiRoot}/foobar`;
      this.http.post(url, foobar, httpOptions)
        .toPromise()
        .then(res => { // Success
          this.foobar = res.json();
          if (foobar.result === this.foobar.result) {
            console.log('Foobar saved');
          } else {
            console.log('All your base are belong to us');
          }
        },
        msg => { // Error
          console.log(msg);
        }
      );
  }
  // ====================================================================================
}
