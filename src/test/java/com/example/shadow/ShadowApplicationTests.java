package com.example.shadow;

import com.example.shadow.chatbot.Repository.*;
import com.example.shadow.chatbot.shadow.entity.*;
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

	@Autowired
	private ShadowRepository shadowRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	@DisplayName("keyword 띄우기")
	void t1() {
		Question question = questionRepository.getById(1L);
		Shadow shadow = shadowRepository.findById(1L).get(); // 쿠팡
		List<Keyword> keywords = shadow.getKeywords(); // 주문, 주문조회, 반품
		List<Flowchart> flowcharts = keywords.get(2).getFlowcharts();
		Flow flow = flowcharts.get(1).getFlow();
		assertThat(flow.getName()).isEqualTo("주문내역");

	}
}