package com.redhat.rhdgmeter.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.benchmarklib.service.BenchmarkService;
import com.redhat.benchmarklib.service.BenchmarkServiceException;
import com.redhat.rhdgmeter.benchmark.BookBenchmark;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/bookbenchmark")
public class BookBenchmarkResource {

    private static final Logger logger = LoggerFactory.getLogger(BookBenchmarkResource.class);

    @ConfigProperty(name = "benchmark.bookbenchmark.warmup-enabled")
    boolean warmupEnabled;

    @ConfigProperty(name = "benchmark.bookbenchmark.running-seconds")
    long runningSeconds;

    @Inject
    BenchmarkService benchmarkService;

    @Inject
    BookBenchmark bookBenchmark;

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String run() {

        try {
            benchmarkService.runBenchmark(bookBenchmark,warmupEnabled,runningSeconds);
        } catch (BenchmarkServiceException e) {
            logger.error("BenchmarkServiceException", e);
            return "BookBenchmark failed";
        }
        return "BookBenchmark finished";
    }
}