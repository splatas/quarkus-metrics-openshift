package com.redhat.rhdgmeter.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.benchmarklib.benchmark.Benchmark;
import com.redhat.rhdgmeter.model.Book;
import com.redhat.rhdgmeter.service.CacheService;
import com.redhat.rhdgmeter.util.Generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.annotation.Timed;

@ApplicationScoped
public class BookBenchmark implements Benchmark{

    private static final Logger logger = LoggerFactory.getLogger(BookBenchmark.class);

    @Inject
    CacheService cacheService;

    // List<Book> bookList = new LinkedList<Book>();
    List<Book> bookList = new ArrayList<Book>();

    @Override
    @Timed("book_benchmark")
    public void run() {
        
        logger.info("--> run BookBenchmark");

        getBook(bookList.get(ThreadLocalRandom.current().nextInt(0, bookList.size())));
    }

    @Override
    public void warmup() {

        // TODO clear cache

        // cacheService.clear();
        populateCache(100);
        
    }

    @Timed("populate_book_cache")
    public void populateCache(int size){
        for(int i=0; i < size; i++){

            Book book = Generator.newBook();
            cacheService.saveBook(book);
            bookList.add(book);
        }
    }

    @Timed("get_book_cache")
    public void getBook(Book book){

        cacheService.getBook(book);
    }

}
