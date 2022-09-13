package com.example.shadow.domain.shadow.repository;

import com.example.shadow.domain.shadow.entity.Flow;
import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowChartRepository extends JpaRepository<Flowchart, Long> {

    Flowchart findByKeyword(long keyword_id);
    List<Flowchart> findByKeyword(Keyword originKeyword);
    Flowchart findTopByKeywordOrderBySeqDesc(Keyword keyword);

    List<Flow> findByFlow(Flowchart flowchart);

    Flowchart findByFlow(Flow flow);

    void deleteByFlow(Flow flow);
}
