package br.com.alphablack.stone.core.cache;

public interface CacheUseCase {
    void put(String key, Object value, int ttlSeconds);
    Object get(String key);
    void delete(String key);
}
