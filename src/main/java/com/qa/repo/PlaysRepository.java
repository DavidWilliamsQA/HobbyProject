package com.qa.repo;

import com.qa.domain.Plays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaysRepository extends JpaRepository<Plays, Long> {
}
