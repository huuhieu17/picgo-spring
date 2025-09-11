package com.steve.Picgo.repositories;

import com.steve.Picgo.entites.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {
    boolean existsTestByEmail(String email);
}
