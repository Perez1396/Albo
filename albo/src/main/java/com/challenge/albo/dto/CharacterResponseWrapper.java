package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CharacterResponseWrapper {
    private Date lastSync;
    private List<CharacterResponse> characters;
}
