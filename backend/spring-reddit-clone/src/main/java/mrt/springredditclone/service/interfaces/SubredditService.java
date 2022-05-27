package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.dto.SubredditDto;

import java.util.List;
import java.util.Optional;

public interface SubredditService {

    SubredditDto save(SubredditDto subredditDto);

    List<SubredditDto> getAll();

    SubredditDto getSubredditById(Long id);
}
