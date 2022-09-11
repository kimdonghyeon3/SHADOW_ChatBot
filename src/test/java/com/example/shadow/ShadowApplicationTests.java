package com.example.shadow;

import com.example.shadow.chat.Repository.*;
import com.example.shadow.domain.shadow.Repository.*;
import com.example.shadow.domain.shadow.entity.Flowchart;
import com.example.shadow.domain.shadow.entity.Keyword;
import com.example.shadow.domain.shadow.entity.Question;
import com.example.shadow.domain.shadow.entity.Shadow;
import com.example.shadow.domain.shadow.service.QuestionService;
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
	private QuestionService questionService;
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
		Question question = questionService.findById(1L);
		Shadow shadow = shadowRepository.findById(1L).get(); // 쿠팡
		List<Keyword> keywords = shadow.getKeywords(); // 주문, 주문조회, 반품
		if(keywords.get(2).getName().equals(question.getKeyword())) {
			List<Flowchart> flowcharts = keywords.get(2).getFlowcharts();
			assertThat(flowcharts.get(1).getFlow().getName()).isEqualTo("주문내역");
		}

	}
}