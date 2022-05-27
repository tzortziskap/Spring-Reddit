import { throwError } from 'rxjs';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CreatePostComponent } from './../post/create-post/create-post.component';
import { SubredditService } from './../../service/subreddit/subreddit.service';
import { Component, OnInit } from '@angular/core';
import { SubredditModel } from 'src/app/service/subreddit/subreddit.response';

@Component({
  selector: 'app-subreddit',
  templateUrl: './subreddit.component.html',
  styleUrls: ['./subreddit.component.css']
})
export class SubredditComponent implements OnInit {
  
  createSubredditForm: FormGroup;
  subredditModel: SubredditModel;
  title = new FormControl('')
  description = new FormControl('');

  constructor(private subredditService:SubredditService, private router: Router) {
    this.createSubredditForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.subredditModel = {
      name: '',
      description: ''
    }
   }

  ngOnInit(): void {
  }

  discard(){
    this.router.navigateByUrl("/");
  }

  createSubreddit(){
    this.subredditModel.name = this.createSubredditForm.get('title')?.value;
    this.subredditModel.description = this.createSubredditForm.get("description")?.value;
    this.subredditService.createSubreddit(this.subredditModel).subscribe(data =>
      this.router.navigateByUrl('/list-subreddits'), error => throwError('Error occured'))
  }

}
