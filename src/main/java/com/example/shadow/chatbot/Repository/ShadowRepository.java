package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.shadow.entity.Shadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShadowRepository extends JpaRepository<Shadow, Long> {

    Optional<Shadow> findByMainurl(String url);

}
