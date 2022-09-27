package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    boolean existsByQuestion(String question);

    List<Question> findByQuestion(String question);

    boolean existsByQuestionAndKeyword(String question, Keyword keyword);

}
