package mrt.springredditclone.repository;

import mrt.springredditclone.model.Comment;
import mrt.springredditclone.model.Post;
import mrt.springredditclone.model.RefreshToken;
import mrt.springredditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
