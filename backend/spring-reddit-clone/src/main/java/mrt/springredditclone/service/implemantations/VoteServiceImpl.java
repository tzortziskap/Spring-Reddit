package mrt.springredditclone.service.implemantations;

import lombok.AllArgsConstructor;
import mrt.springredditclone.dto.VoteDto;
import mrt.springredditclone.exceptions.PostNotFoundException;
import mrt.springredditclone.exceptions.SpringRedditException;
import mrt.springredditclone.model.Post;
import mrt.springredditclone.model.Vote;
import mrt.springredditclone.repository.PostRepository;
import mrt.springredditclone.repository.VoteRepository;
import mrt.springredditclone.service.interfaces.AuthService;
import mrt.springredditclone.service.interfaces.VoteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static mrt.springredditclone.model.VoteType.UPVOTE;

@AllArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Override
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + " 'd for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
