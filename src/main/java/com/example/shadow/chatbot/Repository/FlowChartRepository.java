package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.shadow.entity.Flowchart;
import com.example.shadow.chatbot.shadow.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowChartRepository extends JpaRepository<Flowchart, Long> {

    Flowchart findByKeyword(long keyword_id);

    Flowchart findTopByKeywordOrderBySeqDesc(Keyword keyword);
}
