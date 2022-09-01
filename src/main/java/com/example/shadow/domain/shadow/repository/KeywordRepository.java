package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.member.entity.Member;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Shadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository  extends JpaRepository<Keyword, Long> {


    Keyword findByName(String name);

    Keyword findByNameAndShadow(String name, Shadow shadow);
}
