package mrt.springredditclone.service.implemantations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrt.springredditclone.dto.CommentsDto;
import mrt.springredditclone.exceptions.PostNotFoundException;
import mrt.springredditclone.mapper.CommentMapper;
import mrt.springredditclone.model.Comment;
import mrt.springredditclone.model.NotificationEmail;
import mrt.springredditclone.model.Post;
import mrt.springredditclone.model.User;
import mrt.springredditclone.repository.CommentRepository;
import mrt.springredditclone.repository.PostRepository;
import mrt.springredditclone.repository.UserRepository;
import mrt.springredditclone.service.interfaces.AuthService;
import mrt.springredditclone.service.interfaces.CommentService;
import mrt.springredditclone.service.interfaces.MailContentBuilder;
import mrt.springredditclone.service.interfaces.MailService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private static final String POST_URL = "";
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    @Override
    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    @Override
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    @Override
    public List<CommentsDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() +
                " Commented on your post", user.getEmail(),message));
    }
}
