package com.mayhoo.coinbasepro.impl;

import com.google.gson.Gson;
import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.exchange.Signature;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@Value
@Slf4j
@RequiredArgsConstructor
@ToString(exclude = {"publicKey", "passphrase", "signature", "restTemplate"})
public class CoinbaseProExchangeImpl implements CoinbaseProExchange {
    private static final String GET_METHOD = "GET";
    private static final String DELETE_METHOD = "DELETE";
    private static final String POST_METHOD = "POST";

    String publicKey;
    String passphrase;
    String baseUrl;
    Signature signature;
    RestTemplate restTemplate;

    @Override
    public <T> T get(String resourcePath, Class<T> responseClass) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(getBaseUrl() + resourcePath,
                    GET,
                    securityHeaders(resourcePath,
                            GET_METHOD,
                            ""),
                    responseClass);
            return responseEntity.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("GET request Failed for '" + resourcePath + "': " + ex.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public <T> T pagedGet(String resourcePath,
                          Class<T> responseClass,
                          String beforeOrAfter,
                          Integer pageNumber,
                          Integer limit) {
        resourcePath += "?" + beforeOrAfter + "=" + pageNumber + "&limit=" + limit;
        return get(resourcePath, responseClass);
    }

    @Override
    public <T> T delete(String resourcePath, Class<T> responseClass) {
        try {
            ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + resourcePath,
                    HttpMethod.DELETE,
                    securityHeaders(resourcePath, DELETE_METHOD, ""),
                    responseClass);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("DELETE request Failed for '" + resourcePath + "': " + ex.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public <T, R> T post(String resourcePath, Class<T> responseClass, R jsonObj) {
        Gson gson = new Gson();
        String jsonBody = gson.toJson(jsonObj);

        try {
            ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + resourcePath,
                    HttpMethod.POST,
                    securityHeaders(resourcePath, POST_METHOD, jsonBody),
                    responseClass);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("POST request Failed for '" + resourcePath + "': " + ex.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public HttpEntity<String> securityHeaders(String endpoint, String method, String jsonBody) {
        HttpHeaders headers = new HttpHeaders();

        String timestamp = Instant.now().getEpochSecond() + "";
        String resource = endpoint.replace(getBaseUrl(), "");

        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("CB-ACCESS-KEY", publicKey);
        headers.add("CB-ACCESS-SIGN", signature.generate(resource, method, jsonBody, timestamp));
        headers.add("CB-ACCESS-TIMESTAMP", timestamp);
        headers.add("CB-ACCESS-PASSPHRASE", passphrase);

        curlRequest(method, jsonBody, headers, resource);

        return new HttpEntity<>(jsonBody, headers);
    }

    private void curlRequest(String method, String jsonBody, HttpHeaders headers, String resource) {
        if (!log.isDebugEnabled()) {
            return;
        }
        StringBuilder curlTest = new StringBuilder("curl ");
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                continue;
            }
            curlTest.append("-H '").append(entry.getKey()).append(":").append(entry.getValue().get(0)).append("' ");
        }
        if (!jsonBody.equals(""))
            curlTest.append("-d '").append(jsonBody).append("' ");

        curlTest.append("-X ").append(method).append(" ").append(getBaseUrl()).append(resource);
        log.debug(curlTest.toString());
    }
}