package com.example.shadow.chatbot.service;

import com.example.shadow.chatbot.Repository.QuestionRepository;
import com.example.shadow.chatbot.shadow.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void create(String question, String respMessage) {
        Question t = new Question();
        t.setQuestion(question);
        t.setKeyword(respMessage);
        questionRepository.save(t);
    }

    public boolean existByQuestion(String question) {
        return questionRepository.existsByQuestion(question);
    }

    public Question findByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }
}
