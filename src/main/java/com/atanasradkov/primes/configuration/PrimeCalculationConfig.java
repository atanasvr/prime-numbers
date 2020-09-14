package com.atanasradkov.primes.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "primes")
public class PrimeCalculationConfig {
    private Integer cacheUpperLimit;
    private Integer maxNumberToCalculate;
}
