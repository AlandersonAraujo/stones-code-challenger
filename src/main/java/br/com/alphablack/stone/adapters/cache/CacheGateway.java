package br.com.alphablack.stone.adapters.cache;

public interface CacheGateway {
    void put(String key, Object value, int ttlSeconds);
    Object get(String key);
    void delete(String key);
}
