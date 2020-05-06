package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "playbook")
public class Playbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "playbook_line", joinColumns = {
            @JoinColumn(name = "playbook_id", referencedColumnName = "id",
                    nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "play_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private final List<Plays> plays = new ArrayList<>();

    public Playbook() {
    }

    public Playbook(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long playbook_id) {
        this.id = playbook_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plays> getPlays() {
        return plays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playbook)) return false;
        Playbook playbook = (Playbook) o;
        return getId().equals(playbook.getId()) &&
                getName().equals(playbook.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
