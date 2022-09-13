package com.example.shadow.domain.shadow.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ShadowDto {

    String name;
    String mainurl;
    List<KeywordDto> keyword;

}
