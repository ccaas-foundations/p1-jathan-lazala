package com.revature.routing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        // Ensures JSON responses serialize Java time consistently.
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Your app uses XML only for inbound JMS payloads (via XmlMapper), but REST responses should be JSON.
        converters.removeIf(converter -> converter instanceof MappingJackson2XmlHttpMessageConverter);

        // Ensure a JSON converter backed by our configured ObjectMapper is present.
        boolean hasJsonConverter = converters.stream()
                .anyMatch(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        if (!hasJsonConverter) {
            converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
        }
    }
}