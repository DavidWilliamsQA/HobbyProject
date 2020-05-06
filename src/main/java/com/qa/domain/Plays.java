package com.qa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "plays")
public class Plays implements Serializable {

    @ManyToMany(mappedBy = "plays", fetch = FetchType.LAZY)
    private final List<Playbook> playbooks = new ArrayList<>();
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long play_id) {
        this.id = play_id;
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
        return getId().equals(plays.getId()) &&
                getDescription().equals(plays.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }
}
