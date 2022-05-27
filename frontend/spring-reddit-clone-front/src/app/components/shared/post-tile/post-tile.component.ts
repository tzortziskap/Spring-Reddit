import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { faArrowDown, faArrowUp, faComments } from '@fortawesome/free-solid-svg-icons';
import { Post } from 'src/app/service/post/post.model';
import { PostService } from 'src/app/service/post/post.service';

@Component({
  selector: 'app-post-tile',
  templateUrl: './post-tile.component.html',
  styleUrls: ['./post-tile.component.css']
})
export class PostTileComponent implements OnInit {

  
  faComments = faComments;
  
  posts: Array<Post> = [];
  
  constructor( private postService: PostService, private router: Router) { 
    this.postService.getAllPosts().subscribe(response =>
      this.posts = response);
  }

  ngOnInit(): void {
  }

  goToPost(postId: number){
    this.router.navigateByUrl('/view-post' + postId);
  }
  
}
