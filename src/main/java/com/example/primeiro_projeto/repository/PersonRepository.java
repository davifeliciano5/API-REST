package com.example.primeiro_projeto.repository;

import com.example.primeiro_projeto.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
