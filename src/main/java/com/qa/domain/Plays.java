package com.qa.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "plays")
public class Plays{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "plays", fetch = FetchType.EAGER)
    private final Set<Playbook> playbooks = new HashSet<>();

    public Plays() {
    }

    public Plays(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Plays(String description) {
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

    public Set<Playbook> getPlaybooks() {
        return playbooks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plays)) return false;
        Plays plays = (Plays) o;
        return getId().equals(plays.getId()) &&
                getDescription().equals(plays.getDescription()) &&
                getPlaybooks().equals(plays.getPlaybooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getPlaybooks());
    }
}
