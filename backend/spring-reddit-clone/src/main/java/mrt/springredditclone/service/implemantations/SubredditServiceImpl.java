package mrt.springredditclone.service.implemantations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mrt.springredditclone.dto.SubredditDto;
import mrt.springredditclone.exceptions.SpringRedditException;
import mrt.springredditclone.mapper.SubredditMapper;
import mrt.springredditclone.model.Subreddit;
import mrt.springredditclone.repository.SubredditRepository;
import mrt.springredditclone.service.interfaces.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Override
    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;

    }

    @Transactional(readOnly=true)
    public List<SubredditDto> getAll(){
         return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    @Override
    public SubredditDto getSubredditById(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with these id"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
