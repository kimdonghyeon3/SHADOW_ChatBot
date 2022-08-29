package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    boolean existsByQuestion(String question);

    Question findByQuestion(String question);

    Question findByKeyword(String keyword);
}
