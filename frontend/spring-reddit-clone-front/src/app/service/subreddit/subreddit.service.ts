import { SubredditModel } from 'src/app/service/subreddit/subreddit.response';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {

  baseUrl = "Http://localhost:8080/api/subreddit"
  constructor(private http: HttpClient) { }


  getAllSubrddits(): Observable<SubredditModel[]> {
    return this.http.get<SubredditModel[]>(this.baseUrl);
  }

  createSubreddit(sub: SubredditModel): Observable<SubredditModel> {
    return this.http.post<SubredditModel>(`${this.baseUrl}`, sub);
  }

}
