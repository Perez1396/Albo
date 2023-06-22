package com.challenge.albo.Util;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

final class URLUtil {
    private final String baseUri;
    private final String publicApiKey;
    private final String privateApiKey;
    private final long timestamp;

    URLUtil(String baseUri, String publicApiKey, String privateApiKey) {
        this.baseUri = baseUri;
        this.publicApiKey = publicApiKey;
        this.privateApiKey = privateApiKey;
        this.timestamp = Instant.now().toEpochMilli();
    }

    String builder() throws NoSuchAlgorithmException {
        return baseUri + "?ts=%s&apikey=%s&hash=%s&limit=100";
    }

    private String generateMD5Hash() throws NoSuchAlgorithmException {
        return MD5Util.generateMD5Hash(timestamp + privateApiKey + publicApiKey);
    }
}
