package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.dto.PostRequest;
import mrt.springredditclone.dto.PostResponse;
import mrt.springredditclone.model.Post;

import java.util.List;

public interface PostService {

    Post save(PostRequest postRequest);

    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();

    List<PostResponse> getPostsBySubreddit(Long subredditId);

    List<PostResponse> getPostByUsername(String name);

}
