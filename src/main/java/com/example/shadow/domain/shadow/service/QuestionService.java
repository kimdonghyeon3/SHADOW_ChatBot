package com.example.shadow.domain.shadow.service;

import com.example.shadow.domain.shadow.repository.QuestionRepository;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void create(String question, Keyword keyword) {
        Question t = new Question();
        t.setQuestion(question);
        t.setKeyword(keyword);
        questionRepository.save(t);
    }

    public boolean existByQuestion(String question) {
        return questionRepository.existsByQuestion(question);
    }

    public Question findByQuestion(String question) {
        return questionRepository.findByQuestion(question).orElseThrow(()->new RuntimeException("질문을 찾을 수 없습니다."));
    }

    public Question findById(long id) {
        return questionRepository.findById(id).get();
    }
}
