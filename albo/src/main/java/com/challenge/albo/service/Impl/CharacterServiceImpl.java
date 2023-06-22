package com.challenge.albo.service.Impl;

import com.challenge.albo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class CharacterServiceImpl implements CharacterService {

    private static String PUBLIC_KEY = "f9e8da00cb3e44c035d296d3915e3c66";
    private static String PRIVATE_KEY = "4a262e1eca21ff04dac0069723095ab461af5166";

    @Autowired
    RestTemplate restTemplate;




}
