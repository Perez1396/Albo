package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {
    private String character;
    private List<String> comics;
}
