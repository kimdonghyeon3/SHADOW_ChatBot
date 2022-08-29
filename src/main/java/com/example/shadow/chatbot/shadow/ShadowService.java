package com.example.shadow.chatbot.shadow;

import com.example.shadow.chatbot.Repository.QuestionRepository;
import com.example.shadow.chatbot.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShadowService {
    private final QuestionRepository shadowRepository;

    public void create(String question, String respMessage) {
        Question t = new Question();
        t.setQuestion(question);
        t.setKeyword(respMessage);
        shadowRepository.save(t);
    }

    public boolean existByQuestion(String question) {
        return shadowRepository.existsByQuestion(question);
    }

    public Question findByQuestion(String question) {
        return shadowRepository.findByQuestion(question);
    }
}
