package com.redhat.benchmarklib.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchmarkDelegate implements Runnable{
    
    private static final Logger logger = LoggerFactory.getLogger(BenchmarkDelegate.class);

    private Benchmark benchmark;

    private boolean shutdown = false;

    public BenchmarkDelegate(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public void run(){

        logger.debug("--> run BenchmarkDelegate");
        while (!shutdown) {
            benchmark.run();

        }
        logger.debug("--> Benchmark shutdown");

    }

    public void shutdown() {
        shutdown = true;
    }

}
