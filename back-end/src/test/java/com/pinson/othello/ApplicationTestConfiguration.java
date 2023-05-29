package com.pinson.othello;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = "com.pinson.othello")
public class ApplicationTestConfiguration {
    //
}
