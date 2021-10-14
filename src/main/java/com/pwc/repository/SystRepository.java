package com.pwc.repository;

import com.pwc.model.Syst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystRepository extends JpaRepository<Syst, Integer> {
    @Query("SELECT u FROM Syst u WHERE (u.name) LIKE %?1%")
    public Page<Syst> findAll(String keyword, Pageable pageable);
}
