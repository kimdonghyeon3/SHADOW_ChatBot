package com.example.shadow;

import com.example.shadow.chatbot.Repository.FlowChartRepository;
import com.example.shadow.chatbot.Repository.FlowRepository;
import com.example.shadow.chatbot.Repository.KeywordRepository;
import com.example.shadow.chatbot.entity.Flow;
import com.example.shadow.chatbot.entity.FlowChart;
import com.example.shadow.chatbot.entity.Keyword;
import com.example.shadow.chatbot.entity.Question;
import com.example.shadow.chatbot.Repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShadowApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private KeywordRepository keywordRepository;

	@Autowired
	private FlowChartRepository flowChartRepository;

	@Autowired
	private FlowRepository flowRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("keyword 띄우기")
	void t1() {
		Question q = questionRepository.findById(1L).get();
		String keyword = q.getKeyword(); // "반품"
		Keyword k = keywordRepository.findByName(keyword); //
		long keyword_id = k.getId(); // 1
		Flow f = flowRepository.findById(1L).get();
		List<FlowChart> fc = f.getFlowCharts();
		assertThat(fc.get(0).getFlowChart_uid()).isEqualTo(1);
	}

}