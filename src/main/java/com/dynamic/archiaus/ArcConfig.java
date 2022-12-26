/*
 * Copyright 2022 Play Games24x7 Pvt. Ltd. All Rights Reserved
 */

package com.dynamic.archiaus;


import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ArcConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArcConfig.class);
    private final PolledConfigurationSource polledConfigurationSource;
    private final AbstractPollingScheduler abstractPollingScheduler;

    public ArcConfig(RestConfigurationSource restConfigurationSource) {
        abstractPollingScheduler = new FixedDelayPollingScheduler(10000, 15000, false);
        polledConfigurationSource = restConfigurationSource;
    }

    @PostConstruct
    public void init() {
        AbstractConfiguration configuration = new DynamicConfiguration(polledConfigurationSource, abstractPollingScheduler);
        ConfigurationManager.install(configuration);
    }
}
