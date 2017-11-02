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

import java.util.Map;

/**
 * Created by rayt on 5/1/17.
 */
public class WeatherDataService {
  private final RestTemplate restTemplate;
  private final String endpoint;

  public WeatherDataService(RestTemplate restTemplate, String endpoint) {
    this.restTemplate = restTemplate;
    this.endpoint = endpoint;
  }

  public Map<String, String> currentWeather(String city) {
    String formattedCity = city.toLowerCase().replaceAll("\\s+", "_");
    return restTemplate.getForObject(endpoint + "/" + formattedCity, Map.class);
  }
}
