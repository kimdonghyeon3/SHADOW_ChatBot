package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Shadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowChartRepository  extends JpaRepository<Flowchart, Long> {
}
