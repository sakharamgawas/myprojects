import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Twitter Application';
  result: any;
  styles = { "margin-top": "23px" };
  keys = ""; finalkeys: String[] = [];
  keys2:any;

  getKeywords() {
    console.log("inside getkeywords");
    this.http.get("http://localhost:8080/t/keywords/get",{ responseType: "text" }).subscribe(d => {
      this.keys2 = d;
      console.log(d)
      // Array.from(d).filter(a=>a.startsWith("" && a.endsWith("")))
      // for (let i of d) {
      //   console.log(i);
      // }
    });

  }

  updatefinalkeys() {
    console.log("inside secondary button")
    this.finalkeys = this.keys.split(",");
    let datetime = new Date;
    this.updateKeywords(datetime);
  }
  updateKeywords(datetime: Date) {
    this.finalkeys = this.finalkeys.filter(x => x != "");
    console.log(this.finalkeys, "finalkeyes");
    let body = {
      "keywords": this.finalkeys,
      "updateDate": datetime.toISOString()
    }


    if (this.finalkeys.length > 0)
      this.http.post("http://localhost:8080/t/", body, { responseType: "text" }).subscribe();
    console.log("after post req");

  }
  deleteAllKeywords() {
    this.http.delete("http://localhost:8080/t/keywords/deleteAll", { responseType: "text" }).subscribe((d) => {
      console.log(d);

    });
  }

  deleteAllTweets() {
    this.http.delete("http://localhost:8080/t/tweets/deleteAll", { responseType: "text" }).subscribe((d) => {
      console.log(d);

    });
  }
  deleteThisKeywords() {
    console.log("this keywordsd ")
    this.finalkeys = this.finalkeys.filter(x => x != "");
    let body = {
      "keywords": this.finalkeys
    }
    if (this.finalkeys.length > 0) {
      const options = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
        body: body
      };

      this.http
        .delete("http://localhost:8080/t/keywords/delete", options)
        .subscribe((s) => {
          console.log(s);
        });
    }
  }
  gatherTweets() {
    setTimeout(() => {
      this.http.get("http://localhost:8080/t/tweet", { responseType: "text" }).subscribe(d => console.log(d));
    }, 5000);
  }
  constructor(private http: HttpClient) { }

  async getTweets(data: NgForm) {

    console.log("inside submit form")

    this.http.get("http://localhost:8080/t/tweet/get").subscribe((d) => {
      this.result = d;
    })
  }
}
