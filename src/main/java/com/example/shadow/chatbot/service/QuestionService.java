package com.example.shadow.chatbot.service;

import com.example.shadow.chatbot.Repository.QuestionRepository;
import com.example.shadow.chatbot.shadow.entity.Keyword;
import com.example.shadow.chatbot.shadow.entity.Question;
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
