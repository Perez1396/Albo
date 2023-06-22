package com.challenge.albo.Util;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public final class URIUtil {
    private final String baseUri;
    private final String publicApiKey;
    private final String privateApiKey;
    private final long timestamp;

    public URIUtil(String baseUri, String publicApiKey, String privateApiKey) {
        this.baseUri = baseUri;
        this.publicApiKey = publicApiKey;
        this.privateApiKey = privateApiKey;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public String builder(){
        return baseUri + "?ts=%s&apikey=%s&hash=%s&limit=100";
    }

    private String generateMD5Hash() throws NoSuchAlgorithmException {
        return MD5Util.generateMD5Hash(timestamp + privateApiKey + publicApiKey);
    }
}
