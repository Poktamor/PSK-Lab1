package org.example.repositories;

import org.example.models.Submarine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmarineRepository extends JpaRepository<Submarine, Long> {
    Submarine findByName(String name);
}
