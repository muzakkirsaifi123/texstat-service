package com.github.dpalmasan.metrics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.github.dpalmasan.texts.TextEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadabilityMetricController {
    private static final int MIN_RANGE = 35;
    private static final int MAX_RANGE = 50;
    private static final int TRIALS = 5;

    @Autowired
    @Qualifier("concretenessLexicon")
    private HashMap<String, Double> concretenessLexicon;

    @GetMapping("/metrics")
    List<ReadabilityMetric> computeMetrics(TextEntity text) {
        List<ReadabilityMetric> metrics = Arrays.asList(new ReadabilityMetric[] {
                new ReadabilityMetric(text.getId(), "D-Estimate",
                        MetricLibrary.diversityEstimate(text.getText(), MIN_RANGE, MAX_RANGE, TRIALS)),

                new ReadabilityMetric(text.getId(), "Char-Count",
                        (double) MetricLibrary.charCount(text.getText())),

                new ReadabilityMetric(text.getId(), "Avgerage-Concreteness",
                        MetricLibrary.averageConcreteness(text.getText(), concretenessLexicon))
        });
        return metrics;
    }
}
