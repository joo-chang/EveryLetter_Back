package com.joo.everyletter_back.common.model.repository;

import com.joo.everyletter_back.common.model.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {

    EmailAuth findByEmail(String email);

}