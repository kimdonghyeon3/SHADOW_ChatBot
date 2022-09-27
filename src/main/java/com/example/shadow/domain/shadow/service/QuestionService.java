package com.example.shadow.domain.shadow.service;

import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.repository.QuestionRepository;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Question> findByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }

    public Question findById(long id) {
        return questionRepository.findById(id).get();
    }

    public boolean existByQuestionAndKeyword(String question, Keyword keyword) {
        return questionRepository.existsByQuestionAndKeyword(question,keyword);
    }

    public Question findByQuestionAndShadow(String reqMessage, Shadow shadow) {
        Question question = null;
        List<Question> questions = questionRepository.findByQuestion(reqMessage);
        for(Question q : questions){
            if(q.getKeyword().getShadow().equals(shadow))
                question=q;
        }
        return question;
    }
}
