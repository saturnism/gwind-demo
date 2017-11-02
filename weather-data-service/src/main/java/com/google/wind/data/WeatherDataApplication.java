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
package com.google.wind.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@RestController
public class WeatherDataApplication {
  private final String version = "1.0";

  @Autowired
  private Environment environment;

  @GetMapping("/weather/{city}")
  public Map<String, String> weather(@PathVariable String city) {
    Map<String, String> response = new HashMap<>();

    response.put("city", city);
    response.put("condition", environment.getProperty(String.format("weather.%s", city), "Maybe Supposedly Not Cloudy"));

    return response;
  }

  public static void main(String[] args) {
    SpringApplication.run(WeatherDataApplication.class, args);
  }
}
