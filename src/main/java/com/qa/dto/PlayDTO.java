package com.qa.dto;

import com.qa.domain.Playbook;

import java.util.ArrayList;
import java.util.List;

public class PlayDTO {

    private Long id;
    private String description;
    private List<PlaybookDTO> playbooks;

    public PlayDTO() {
    }

    public PlayDTO(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PlaybookDTO> getPlaybooks() {
        return playbooks;
    }

    public void setPlaybooks(List<PlaybookDTO> playbooks) {
        this.playbooks = playbooks;
    }
}
