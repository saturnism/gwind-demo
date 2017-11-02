/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.wind.ui;

import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rayt on 5/1/17.
 */
public class WeatherControlService {
  private final RestTemplate restTemplate;
  private final String endpoint;
  private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  public WeatherControlService(RestTemplate restTemplate, String endpoint) {
    this.restTemplate = restTemplate;
    this.endpoint = endpoint;
  }

  public Map<String, String> request(Map<String, String> request) {
    return restTemplate.postForObject(endpoint, request, Map.class);
  }

  public List<Map> all() {
    Map response = restTemplate.getForObject(endpoint, Map.class);

    Map embedded = (Map) response.get("_embedded");
    List<Map> messages = (List<Map>) embedded.get("weatherControlLogs");
    return messages.stream()
        .filter(message -> message.containsKey("_links"))
        .map(message -> (Map) message.get("_links"))
        .filter(links -> links.containsKey("self"))
        .map(links -> (Map) links.get("self"))
        .map(self -> (String) self.get("href"))
        .map(href -> restTemplate.getForObject(href, Map.class))
        .collect(Collectors.toList());
  }
}
