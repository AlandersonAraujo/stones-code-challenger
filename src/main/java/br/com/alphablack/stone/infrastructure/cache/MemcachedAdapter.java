package br.com.alphablack.stone.infrastructure.cache;

import br.com.alphablack.stone.adapters.cache.CacheGateway;
import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;

public class MemcachedAdapter implements CacheGateway {

    private MemcachedClient memcachedClient;

    public MemcachedAdapter(String host, int port) {
        try {
            this.memcachedClient = new MemcachedClient(new InetSocketAddress(host, port));
        } catch (Exception e) {
            throw new RuntimeException("Error initializing MemcachedAdapter", e);
        }
    }

    @Override
    public void put(String key, Object value, int ttlSeconds) {
        this.memcachedClient.set(key, ttlSeconds, value);
    }

    @Override
    public Object get(String key) {
        return memcachedClient.get(key);
    }

    @Override
    public void delete(String key) {
        this.memcachedClient.delete(key);
    }
}
