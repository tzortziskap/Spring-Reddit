import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/auth/login/login.component'
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './components/home/home.component'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SideBarComponent } from './components/shared/side-bar/side-bar.component';
import { SubredditSideBarComponent } from './components/shared/subreddit-side-bar/subreddit-side-bar.component';
import { VoteButtonComponent } from './components/shared/vote-button/vote-button.component'
import { PostTileComponent } from './components/shared/post-tile/post-tile.component';
import { SubredditComponent } from './components/subreddit/subreddit.component';
import { CreatePostComponent } from './components/post/create-post/create-post.component';
import { SubredditListComponent } from './components/subreddit-list/subreddit-list.component';
import { EditorModule } from '@tinymce/tinymce-angular';
import { ViewPostComponent } from './components/view-post/view-post.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    PostTileComponent,
    SideBarComponent,
    SubredditSideBarComponent,
    VoteButtonComponent,
    SubredditComponent,
    CreatePostComponent,
    SubredditListComponent,
    ViewPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    FontAwesomeModule,
    EditorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
