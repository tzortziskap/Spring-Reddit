import { SignupComponent } from './components/auth/signup/signup.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { CreatePostComponent } from './components/post/create-post/create-post.component';
import { SubredditComponent } from './components/subreddit/subreddit.component';
import { SubredditListComponent } from './components/subreddit-list/subreddit-list.component';
import { ViewPostComponent } from './components/view-post/view-post.component';

const routes: Routes = [
  {
    path: 'sign-up', component: SignupComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'create-post', component: CreatePostComponent
  },
  {
    path: 'create-subreddit', component: SubredditComponent
  },
  {
    path: 'list-subreddits', component: SubredditListComponent
  },
  {
    path: '', component: HomeComponent
  },
  {
    path: 'view-post/:id', component: ViewPostComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
