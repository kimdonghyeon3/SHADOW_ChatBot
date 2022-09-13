package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Shadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findByName(String keyword);
    List<Keyword> findByShadow(Shadow shadow);

    List<Keyword> findByShadowAndFavorite(Shadow shadow, boolean b);

    //Optional<Keyword> findByNameAndShadow(String name,Shadow shadow);
    Keyword findByNameAndShadow(String name, Shadow shadow);

}
