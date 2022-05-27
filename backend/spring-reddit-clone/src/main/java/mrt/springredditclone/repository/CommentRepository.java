package mrt.springredditclone.repository;

import mrt.springredditclone.dto.CommentsDto;
import mrt.springredditclone.model.Comment;
import mrt.springredditclone.model.Post;
import mrt.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
