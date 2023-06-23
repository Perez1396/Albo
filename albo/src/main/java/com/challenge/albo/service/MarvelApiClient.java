package com.challenge.albo.service;

import com.challenge.albo.Util.MD5Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class MarvelApiClient {
    private static final String API_BASE_URL = "https://gateway.marvel.com/v1/public/";
    private static final String API_PUBLIC_KEY = "f9e8da00cb3e44c035d296d3915e3c66";
    private static final String API_PRIVATE_KEY = "4a262e1eca21ff04dac0069723095ab461af5166";


    public static String sendRequest(String endpoint) throws IOException, NoSuchAlgorithmException {
        String fullUrl = API_BASE_URL + endpoint + "?apikey=" + API_PUBLIC_KEY;
        long timestamp = System.currentTimeMillis();
        String hash = MD5Util.generateMD5Hash(timestamp+API_PRIVATE_KEY+API_PUBLIC_KEY);
        fullUrl += "&ts=" + timestamp + "&hash=" + hash;
        URL url = new URL(fullUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }
}