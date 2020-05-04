package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Plays {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long play_id;
    private String description;

    public Long getPlay_id() {
        return play_id;
    }

    public void setPlay_id(Long play_id) {
        this.play_id = play_id;
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
        if (!(o instanceof Plays)) return false;
        Plays plays = (Plays) o;
        return getPlay_id().equals(plays.getPlay_id()) &&
                getDescription().equals(plays.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlay_id(), getDescription());
    }
}
