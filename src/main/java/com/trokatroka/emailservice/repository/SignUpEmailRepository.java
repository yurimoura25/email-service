package com.trokatroka.emailservice.repository;

import com.trokatroka.emailservice.model.SignUpEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SignUpEmailRepository extends JpaRepository<SignUpEmail, UUID> {

    public Optional<SignUpEmail> findByEmail(String email);
}
