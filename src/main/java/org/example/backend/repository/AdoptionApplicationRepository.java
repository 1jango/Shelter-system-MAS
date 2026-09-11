package org.example.backend.repository;

import org.example.backend.model.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {}