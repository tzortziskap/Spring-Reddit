import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { Post } from './../../../service/post/post.model';
import { Component, Input, OnInit } from '@angular/core';
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.css']
})
export class VoteButtonComponent implements OnInit {

  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  @Input() post!: Post;
  constructor() { }

  ngOnInit(): void {
  }

  upvotePost(){

  }

  downvotePost(){

  }

}
