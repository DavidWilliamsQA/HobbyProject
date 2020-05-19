package com.qa.dto;


import java.util.Objects;

public class PlayDTO {

    private Long id;
    private String description;

    public PlayDTO() {
    }

    public PlayDTO(Long id, String description) {
        this.id = id;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayDTO)) return false;
        PlayDTO playDTO = (PlayDTO) o;
        return getId().equals(playDTO.getId()) &&
                getDescription().equals(playDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }
}
