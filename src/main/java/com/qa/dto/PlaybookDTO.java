package com.qa.dto;

import com.qa.domain.Plays;

import java.util.*;

public class PlaybookDTO {

    private Long id;
    private String name;

    private Set<PlayDTO> plays = new HashSet<>();

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

    public Set<PlayDTO> getPlays() {
        return plays;
    }

    public void setPlays(Set<PlayDTO> plays) {
        this.plays = plays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaybookDTO)) return false;
        PlaybookDTO that = (PlaybookDTO) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getPlays().equals(that.getPlays());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPlays());
    }
}
