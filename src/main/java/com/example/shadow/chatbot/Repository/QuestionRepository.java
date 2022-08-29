package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.shadow.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    boolean existsByQuestion(String question);

    Question findByQuestion(String question);
}
