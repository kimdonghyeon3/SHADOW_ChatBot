package com.example.shadow.domain.shadow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeywordDto {

    String name;
    List<String> flow;
    List<String> description;
    List<String> url;
    String favorite;
}
