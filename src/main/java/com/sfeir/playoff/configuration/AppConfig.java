package com.sfeir.playoff.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;

@Configuration
public class AppConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return json()
        .findModulesViaServiceLoader(true)
        .propertyNamingStrategy(SNAKE_CASE)
        .featuresToEnable(WRITE_BIGDECIMAL_AS_PLAIN // see DecimalSerializationProp for test cases
            , FAIL_ON_NUMBERS_FOR_ENUMS) // disallow deserializing by ordinal (like `entity_type: "0"`)
        .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
        .serializationInclusion(NON_ABSENT)
        .build();
  }
}