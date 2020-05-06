package com.qa.dto;

import com.qa.domain.Plays;

import java.util.ArrayList;
import java.util.List;

public class PlaybookDTO {

    private Long id;
    private String name;
    private List<PlayDTO> plays = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayDTO> getPlays() {
        return plays;
    }

    public void setPlays(List<PlayDTO> plays) {
        this.plays = plays;
    }
}
