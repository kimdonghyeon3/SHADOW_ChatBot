package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.shadow.entity.Keyword;
import com.example.shadow.chatbot.shadow.entity.Shadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Keyword findByName(String keyword);
    List<Keyword> findByShadow(Shadow shadow);

    List<Keyword> findByShadowAndFavorite(Shadow shadow, boolean b);
}
