package com.redhat.benchmarklib.service;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.benchmarklib.benchmark.Benchmark;
import com.redhat.benchmarklib.benchmark.BenchmarkDelegate;

import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class BenchmarkServiceImpl implements BenchmarkService {

    private static final Logger logger = LoggerFactory.getLogger(BenchmarkServiceImpl.class);

    @Inject
    ManagedExecutor managedExecutor;

    @Override
    public void runBenchmark(Benchmark benchmark,boolean warmupEnabled, long runningSeconds) throws BenchmarkServiceException {
        
        BenchmarkDelegate benchmarkDelegate = new BenchmarkDelegate(benchmark);

        if (warmupEnabled){
            logger.info("Running {} warmup",benchmark.getClass().getSimpleName());
            benchmark.warmup();
        }

        logger.info("Running {} benchmark",benchmark.getClass().getSimpleName());
        managedExecutor.execute(benchmarkDelegate);

        if (runningSeconds > 0) {

            try {
                TimeUnit.SECONDS.sleep(runningSeconds);
            } catch (NumberFormatException e) {
                logger.error("NumberFormatException", e);
                throw new BenchmarkServiceException("NumberFormatException",e);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
                throw new BenchmarkServiceException("InterruptedException",e);
            }
            benchmarkDelegate.shutdown();
        }
    }

}
