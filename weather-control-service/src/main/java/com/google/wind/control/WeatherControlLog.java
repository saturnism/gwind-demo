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
package com.google.wind.control;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class WeatherControlLog {
  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String city;
  private String desiredCondition;
  private String desiredTemperature;
  private String desiredTemperatureUnit;
  private Date timestamp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDesiredCondition() {
    return desiredCondition;
  }

  public void setDesiredCondition(String desiredCondition) {
    this.desiredCondition = desiredCondition;
  }

  public String getDesiredTemperature() {
    return desiredTemperature;
  }

  public void setDesiredTemperature(String desiredTemperature) {
    this.desiredTemperature = desiredTemperature;
  }

  public String getDesiredTemperatureUnit() {
    return desiredTemperatureUnit;
  }

  public void setDesiredTemperatureUnit(String desiredTemperatureUnit) {
    this.desiredTemperatureUnit = desiredTemperatureUnit;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
