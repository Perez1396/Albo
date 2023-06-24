package com.challenge.albo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ComicResponse {
    private Date lastSync;
    private List<String> editors;
    private List<String> writers;
    private List<String> colorists;
}
