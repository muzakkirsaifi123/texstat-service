package com.github.dpalmasan.metrics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class Config {
    private final static Logger logger = LoggerFactory.getLogger(Config.class);

    @Value("classpath:concreteness_dict.txt")
    // or @Value("file:#{systemProperties.mapping}")
    private Resource file;

    @Bean(name = "concretenessLexicon")
    public HashMap<String, Double> getMapping() {
        HashMap<String, Double> mapping = new HashMap<String, Double>();
        try (Scanner sc = new Scanner(file.getInputStream())) {
            while (sc.hasNextLine()) {
                try {
                    mapping.put(sc.next(), sc.nextDouble());
                } catch (Exception e) {
                    logger.debug("Word not supported", e);
                }
            }
        } catch (IOException e) {
            logger.error("could not load mapping file", e);
        }
        return mapping;
    }

}