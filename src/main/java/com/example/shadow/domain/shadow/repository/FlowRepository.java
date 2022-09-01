package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.shadow.entity.Flow;
import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Shadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

    Flow findByNameAndDescription(String name, String description);

    Flow findByName(String name);

}
