package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.dto.VoteDto;
import mrt.springredditclone.model.Post;
import mrt.springredditclone.model.Vote;

public interface VoteService {

    void vote(VoteDto voteDto);

}
