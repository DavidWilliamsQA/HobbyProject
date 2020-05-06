package com.qa.services;

import com.qa.domain.Playbook;
import com.qa.domain.Plays;
import com.qa.dto.PlaybookDTO;
import com.qa.exception.PlayNotFoundException;
import com.qa.exception.PlaybookNotFoundException;
import com.qa.repo.PlaybookRepository;
import com.qa.repo.PlaysRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaybookService {

    private final PlaybookRepository repo;

    private final PlaysRepository playsRepository;

    private final ModelMapper mapper;

    @Autowired
    public PlaybookService(PlaybookRepository playbook, PlaysRepository playsRepository, ModelMapper mapper) {
        this.repo = playbook;
        this.playsRepository = playsRepository;
        this.mapper = mapper;
    }

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    public List<PlaybookDTO> readPlaybooks(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PlaybookDTO createPlaybook(Playbook playbook){
        Playbook tempPlaybook = this.repo.save(playbook);
        return this.mapToDTO(tempPlaybook);
    }

    public PlaybookDTO findPlaybookById(Long id){
        return this.mapToDTO(this.repo.findById(id).orElseThrow(PlaybookNotFoundException::new));
    }

    public PlaybookDTO addPlays(Long id, Long playId){
        Playbook addToPlaybook = this.repo.findById(id).orElseThrow(PlaybookNotFoundException::new);
        Plays findPlay = this.playsRepository.findById(playId).orElseThrow(PlayNotFoundException::new);
        addToPlaybook.getPlays().add(findPlay);
        Playbook tempPlaybook = this.repo.save(addToPlaybook);
        return this.mapToDTO(tempPlaybook);
    }

    public PlaybookDTO updatePlaybook(Long id, Playbook playbook){
        Playbook update = this.repo.findById(id).orElseThrow(PlaybookNotFoundException::new);
        update.setName(playbook.getName());
        Playbook tempPlaybook = this.repo.save(update);
        return this.mapToDTO(tempPlaybook);
    }

    public boolean deletePlaybook(Long id){
        if(!this.repo.existsById(id)){
            throw new PlaybookNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }


}
