package com.qa.rest;

import com.qa.repo.PlaysRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {

    private final PlaysRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public PlayService(PlaysRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

}
