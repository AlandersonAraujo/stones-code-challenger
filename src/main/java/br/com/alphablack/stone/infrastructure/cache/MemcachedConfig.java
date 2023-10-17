package br.com.alphablack.stone.infrastructure.cache;

import br.com.alphablack.stone.adapters.cache.CacheGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemcachedConfig {

    @Value("${memcached.host}")
    private String memcachedHost;

    @Value("${memcached.port}")
    private int memcachedPort;

    @Bean
    public CacheGateway cacheGateway() {
        return new MemcachedAdapter(memcachedHost, memcachedPort);
    }
}
