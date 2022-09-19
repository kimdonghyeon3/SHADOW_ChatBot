package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.shadow.entity.Flow;
import com.example.shadow.domain.shadow.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {
    Flow findByName(String name);

    Flow findByNameAndKeyword(String name, Long keyword);
}
