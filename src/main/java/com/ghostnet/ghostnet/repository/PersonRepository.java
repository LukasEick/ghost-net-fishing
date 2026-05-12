package com.ghostnet.ghostnet.repository;

import com.ghostnet.ghostnet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByNameAndPhone(String name, String phone);
}