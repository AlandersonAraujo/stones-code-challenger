package br.com.alphablack.stone.application.cache;

import br.com.alphablack.stone.adapters.cache.CacheGateway;
import br.com.alphablack.stone.core.cache.CacheUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheService implements CacheUseCase {

    private final CacheGateway cacheGateway;

    @Autowired
    public CacheService(CacheGateway cacheGateway) {
        this.cacheGateway = cacheGateway;
    }

    @Override
    public void put(String key, Object value, int ttlSeconds) {
        this.cacheGateway.put(key, value, ttlSeconds);
    }

    @Override
    public Object get(String key) {
        return this.cacheGateway.get(key);
    }

    @Override
    public void delete(String key) {
        this.cacheGateway.delete(key);
    }
}
