package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Playbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playbook_id;
    private Long user_id;
    private String name;

    public Long getPlaybook_id() {
        return playbook_id;
    }

    public void setPlaybook_id(Long playbook_id) {
        this.playbook_id = playbook_id;
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
        return getPlaybook_id().equals(playbook.getPlaybook_id()) &&
                getUser_id().equals(playbook.getUser_id()) &&
                getName().equals(playbook.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaybook_id(), getUser_id(), getName());
    }
}
