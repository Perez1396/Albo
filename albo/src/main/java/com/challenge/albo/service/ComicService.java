package com.challenge.albo.service;

import com.challenge.albo.dto.ComicResponse;

public interface ComicService {
    ComicResponse getComicById(Long idCharacter);
}
