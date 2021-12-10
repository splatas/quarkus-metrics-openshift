package com.redhat.rhdgmeter;

import javax.inject.Inject;

import com.redhat.benchmarklib.service.BenchmarkService;
import com.redhat.rhdgmeter.benchmark.BookBenchmark;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.QuarkusApplication;

// @QuarkusMain
public class ApplicationMain implements QuarkusApplication{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

    @ConfigProperty(name = "benchmark.bookbenchmark.warmup-enabled")
    boolean warmupEnabled;

    @ConfigProperty(name = "benchmark.bookbenchmark.running-seconds")
    long runningSeconds;
    
    @Inject
    BenchmarkService benchmarkService;

    @Inject
    BookBenchmark bookBenchmark;

    @Override
    public int run(String... args) throws Exception {

        benchmarkService.runBenchmark(bookBenchmark,warmupEnabled,runningSeconds);
        
        return 0;
    }


    
}
