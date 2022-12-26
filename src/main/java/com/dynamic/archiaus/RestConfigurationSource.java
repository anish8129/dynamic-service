/*
 * Copyright 2022 Play Games24x7 Pvt. Ltd. All Rights Reserved
 */

package com.dynamic.archiaus;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestConfigurationSource implements PolledConfigurationSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfigurationSource.class);

    @Value("${service.name}")
    private String serviceName;

    public RestConfigurationSource() {
    }

    @Override
    public PollResult poll(boolean initial, Object checkPoint) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KeyValueDetails[]> responseEntity = null;
        try {
             responseEntity = restTemplate.exchange(
                    String.format("http://localhost:9090/getAllKeyValueFor/%s", serviceName),
                    HttpMethod.GET,
                    entity,
                    KeyValueDetails[].class
            );
        } catch (Exception e) {}

        Map<String, Object> collect = Arrays.stream(
                Optional.ofNullable(responseEntity)
                        .map(HttpEntity::getBody)
                        .orElse(new KeyValueDetails[0])
                ).collect(Collectors.toMap(KeyValueDetails::getKey, KeyValueDetails::getValue));

        return PollResult.createFull(collect);
    }
}
