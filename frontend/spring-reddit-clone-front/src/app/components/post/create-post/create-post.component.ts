import { PostService } from './../../../service/post/post.service';
import { SubredditService } from './../../../service/subreddit/subreddit.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { CreatePostPayload } from './create-post.payload';
import { SubredditModel } from 'src/app/service/subreddit/subreddit.response';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  createPostForm!: FormGroup;
  postPayload: CreatePostPayload;
  subreddits: SubredditModel[] = [];

  constructor(private router: Router, private postService: PostService,
    private subredditService: SubredditService) {
    this.postPayload = {
      postName: '',
      url: '',
      description: '',
      subredditName: ''
    }
  }

  ngOnInit() {
    this.createPostForm = new FormGroup({
      postName: new FormControl('', Validators.required),
      subredditName: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
    });
    this.subredditService.getAllSubrddits().subscribe((data) => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    });
  }

  createPost() {
    this.postPayload.postName = this.createPostForm.get('postName')?.value;
    this.postPayload.subredditName = this.createPostForm.get('subredditName')?.value;
    this.postPayload.url = this.createPostForm.get('url')?.value;
    this.postPayload.description = this.createPostForm.get('description')?.value;

    this.postService.createPost(this.postPayload).subscribe(data => {
      this.router.navigateByUrl('/');
    }, error => {
      throwError(error);
    })
  }

  discardPost() {
    this.router.navigateByUrl('/');
  }

}