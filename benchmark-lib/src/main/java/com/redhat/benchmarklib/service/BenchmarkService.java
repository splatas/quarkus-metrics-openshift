package com.redhat.benchmarklib.service;

import com.redhat.benchmarklib.benchmark.Benchmark;

public interface BenchmarkService {

    //TODO concurrent running

    public void runBenchmark(Benchmark benchmark, boolean warmupEnabled,long runningSeconds) throws BenchmarkServiceException;
    
    
}
