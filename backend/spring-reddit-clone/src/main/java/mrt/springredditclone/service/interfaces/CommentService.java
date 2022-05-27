package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.dto.CommentsDto;
import mrt.springredditclone.model.Comment;

import java.util.List;

public interface CommentService {

    void save(CommentsDto commentsDto);

    List<CommentsDto> getAllCommentsForPost(Long postId);

    List<CommentsDto> getAllCommentsForUser(String username);
}
