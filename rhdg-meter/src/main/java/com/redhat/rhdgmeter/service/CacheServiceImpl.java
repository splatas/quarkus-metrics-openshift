package com.redhat.rhdgmeter.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.rhdgmeter.model.Book;

import org.infinispan.client.hotrod.DefaultTemplate;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.annotation.Timed;
import io.quarkus.infinispan.client.Remote;

@ApplicationScoped
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Inject
    @Remote("bookCache")
    RemoteCache<String,Book> bookCache;

    @Inject
    RemoteCacheManager remoteCacheManager;

    @Override
    @Timed("cache_service_savebook")
    public Book saveBook(Book book) {
        
        return bookCache.put(book.getId(), book);
    }

    @Override
    @Timed("cache_service_getbook")
    public Book getBook(Book book) {
        return bookCache.get(book.getId());
    }

    @Override
    @Timed("cache_service_getbooks")
    public List<Book> getBooks() {

        return bookCache.values().stream().collect(Collectors.toList());
    }

    @Override
    public void clear() {

        bookCache.clear();
    }

    @Override
    public void createCache(String cacheName) {

        logger.info("--> Creating cache: {}",cacheName);

        remoteCacheManager.administration().getOrCreateCache(cacheName, DefaultTemplate.DIST_SYNC);
        
    }
    
}
