package io.pivotal.pccclient.config;

import io.pivotal.pccclient.region.Car;
import io.pivotal.spring.cloud.service.gemfire.GemfireServiceConnectorConfig;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableLogging;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@Configuration
@EnableEntityDefinedRegions(basePackages = { "io.pivotal.pccclient.region" })
@EnableGemfireRepositories(basePackages = { "io.pivotal.pccclient.repository" })
@EnableLogging(logLevel = "info")
@EnableCaching(proxyTargetClass = true)
@ClientCacheApplication()
@EnablePdx
public class CacheConfiguration extends AbstractCloudConfig {

	@Bean
	ClientRegionFactoryBean<Long, Car> f1Region(@Autowired ClientCache gemfireCache) {
		ClientRegionFactoryBean<Long, Car> f1Region = new ClientRegionFactoryBean<>();
		f1Region.setName("formula1region");
		f1Region.setCache(gemfireCache);
		f1Region.setClose(false);
		f1Region.setShortcut(ClientRegionShortcut.PROXY);
		f1Region.setLookupEnabled(true);
		return f1Region;
	}

	@Bean
	public ServiceConnectorConfig createGemfireConnectorConfig() {
		GemfireServiceConnectorConfig gemfireConfig = new GemfireServiceConnectorConfig();
		gemfireConfig.setPoolSubscriptionEnabled(true);
		gemfireConfig.setPdxSerializer(new ReflectionBasedAutoSerializer());
		return gemfireConfig;
	}

	@Bean
	public ClientCache gemfireCache(ServiceConnectorConfig serviceConnectorConfig) {
		return cloud().getServiceConnector("f1pccservice", ClientCache.class,
				serviceConnectorConfig);

	}

}