package com.qa.services;

import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.exception.PlayNotFoundException;
import com.qa.repo.PlaysRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayService {

    private final PlaysRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public PlayService(PlaysRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public PlayDTO mapToDTO(Plays plays){
        return this.mapper.map(plays, PlayDTO.class);
    }

    public List<PlayDTO> readPlays(){
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PlayDTO createPlays(Plays plays){
        Plays tempPlay = this.repo.save(plays);
        return this.mapToDTO(tempPlay);
    }

    public PlayDTO findPlayById(Long id){
        return this.mapToDTO(this.repo.findById(id).orElseThrow(PlayNotFoundException::new));
    }

    public PlayDTO updatePlay(Long id, Plays plays){
        Plays update = this.repo.findById(id).orElseThrow(PlayNotFoundException::new);
        update.setDescription(plays.getDescription());
        Plays tempPlay = this.repo.save(plays);
        return this.mapToDTO(tempPlay);
    }

    public boolean deletePlay(Long id){
        if(!this.repo.existsById(id)){
            throw new PlayNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
