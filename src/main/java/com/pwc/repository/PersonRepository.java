package com.pwc.repository;

import com.pwc.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT u FROM Person u WHERE CONCAT(u.name, ' ', u.surname) LIKE %?1%")
    public Page<Person> findAll(String keyword, Pageable pageable);
}
