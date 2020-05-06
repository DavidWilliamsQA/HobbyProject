package com.qa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "playbook")
public class Playbook {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "playbook_line", joinColumns = {
            @JoinColumn(name = "playbook_id", referencedColumnName = "id",
                    nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "play_id", referencedColumnName = "id",
                    nullable = false, updatable = false)})
    private final List<Plays> plays = new ArrayList<>();
    private Long user_id;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long playbook_id) {
        this.id = playbook_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playbook)) return false;
        Playbook playbook = (Playbook) o;
        return getId().equals(playbook.getId()) &&
                getUser_id().equals(playbook.getUser_id()) &&
                getName().equals(playbook.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getName());
    }
}
