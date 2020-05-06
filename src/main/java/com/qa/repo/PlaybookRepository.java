package com.qa.repo;

import com.qa.domain.Playbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaybookRepository extends JpaRepository<Playbook, Long> {
}
