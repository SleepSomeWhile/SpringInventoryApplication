package com.pwc.repository;

import com.pwc.model.Proc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProcRepository extends JpaRepository<Proc, Integer> {
    @Query("SELECT u FROM Proc u WHERE (u.name) LIKE %?1%")
    public Page<Proc> findAll(String keyword, Pageable pageable);
}
