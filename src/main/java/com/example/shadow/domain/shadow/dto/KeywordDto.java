package com.example.shadow.domain.shadow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeywordDto {

    String name;  //반품, 배송조회
    List<FlowDto> flow;
//    List<String> flow; // 홈버튼, 마이쿠팡, ㅇ
//    List<String> description; //
//    List<String> url; //
    Boolean favorite;
}
