package com.example.shadow.domain.shadow.Repository;

import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowChartRepository extends JpaRepository<Flowchart, Long> {

    Flowchart findByKeyword(long keyword_id);

    Flowchart findTopByKeywordOrderBySeqDesc(Keyword keyword);
}
