package com.pranjal.SpringApplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranjal.SpringApplication.models.Authority;
import com.pranjal.SpringApplication.repositories.AuthorityRepository;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority save(Authority authority){
        return authorityRepository.save(authority);

    }

    public Optional<Authority> findById(Long id){
        return authorityRepository.findById(id);
    }

}

