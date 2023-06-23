package com.challenge.albo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class CharacterResponse {
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastSync;
    private List<String> comics;
}