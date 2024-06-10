package com.pranjal.SpringApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pranjal.SpringApplication.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    
}
