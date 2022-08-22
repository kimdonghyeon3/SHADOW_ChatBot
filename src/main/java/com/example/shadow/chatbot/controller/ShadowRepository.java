package com.example.shadow.chatbot.controller;

import com.example.shadow.chatbot.test.Test_Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShadowRepository extends JpaRepository<Test_Keyword, Integer> {

    boolean existsByQuestion(String question);

    Test_Keyword findByQuestion(String question);
}
