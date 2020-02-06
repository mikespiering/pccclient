package io.pivotal.pccclient.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableLogging;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@Configuration
@EnableEntityDefinedRegions(basePackages = { "io.pivotal.pccclient.region" })
@EnableGemfireRepositories(basePackages = { "io.pivotal.pccclient.repository" })
@EnableLogging(logLevel = "info")
@EnableCaching
@EnablePdx
public class CacheConfiguration  {
}
