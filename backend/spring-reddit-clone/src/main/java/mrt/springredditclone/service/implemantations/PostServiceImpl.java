package mrt.springredditclone.service.implemantations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrt.springredditclone.dto.PostRequest;
import mrt.springredditclone.dto.PostResponse;
import mrt.springredditclone.exceptions.PostNotFoundException;
import mrt.springredditclone.exceptions.SubredditNotFoundException;
import mrt.springredditclone.mapper.PostMapper;
import mrt.springredditclone.model.Post;
import mrt.springredditclone.model.Subreddit;
import mrt.springredditclone.model.User;
import mrt.springredditclone.repository.PostRepository;
import mrt.springredditclone.repository.SubredditRepository;
import mrt.springredditclone.repository.UserRepository;
import mrt.springredditclone.service.interfaces.AuthService;
import mrt.springredditclone.service.interfaces.PostService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostServiceImpl implements PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
        public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();
        return postRepository.save(postMapper.map(postRequest, subreddit, currentUser));
    }

    @Transactional(readOnly = true)
    @Override
    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostResponse> getPostByUsername(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
