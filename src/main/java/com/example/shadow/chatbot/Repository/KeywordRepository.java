package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.shadow.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Keyword findByName(String keyword);
}
