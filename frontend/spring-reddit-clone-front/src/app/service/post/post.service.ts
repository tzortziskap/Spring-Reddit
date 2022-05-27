import { CreatePostPayload } from './../../components/post/create-post/create-post.payload';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseUrl = "http://localhost:8080/api/posts";

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]>{
    return this.http.get<Post[]>(`${this.baseUrl}`);
  }

  createPost(postPayload: CreatePostPayload): Observable<any>{
    return this.http.post(this.baseUrl, postPayload);
  }
}
