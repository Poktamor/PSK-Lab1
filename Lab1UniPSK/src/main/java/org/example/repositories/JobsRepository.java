package org.example.repositories;

import org.example.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
    Job findByName(String name);
}
