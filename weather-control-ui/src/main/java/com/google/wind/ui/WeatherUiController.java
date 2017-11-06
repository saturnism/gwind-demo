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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rayt on 5/1/17.
 */
@Controller
@SessionAttributes({"name", "city"})
public class WeatherUiController {
  private final WeatherDataService weatherDataService;
  private final WeatherControlService weatherControlService;

  public WeatherUiController(WeatherDataService weatherDataService, WeatherControlService weatherControlService) {
    this.weatherDataService = weatherDataService;
    this.weatherControlService = weatherControlService;
  }

  @GetMapping("/")
  public String index(Model model) {
    if (model.containsAttribute("city")) {
      String city = (String) model.asMap().get("city");
      Map<String, String> weatherData = weatherDataService.currentWeather(city);
      model.addAttribute("weatherData", weatherData);
    }

    model.addAttribute("weatherControlLogs", weatherControlService.all());

    return "index";
  }

  @PostMapping("/request")
  public String request(@RequestParam String name, @RequestParam String city, @RequestParam String condition, Model model) {
    model.addAttribute("name", name);
    model.addAttribute("city", city);

    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(city)) {
      return "redirect:/";
    }

    Map<String, String> req = new HashMap<>();
    req.put("name", name);
    req.put("city", city);
    req.put("desiredCondition", condition);
    weatherControlService.request(req);

    return "redirect:/";
  }
}
