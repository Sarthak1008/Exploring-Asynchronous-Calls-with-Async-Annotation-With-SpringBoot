package com.javatechie.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // Enables Spring's asynchronous capabilities
public class AsyncConfiguration {

    // Configures an Executor bean for asynchronous task execution
    @Bean("asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        // Create a ThreadPoolTaskExecutor instance
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // Set the core pool size to 4 (initial number of threads in the pool)
        taskExecutor.setCorePoolSize(4);

        // Set the maximum queue capacity to 150 (maximum number of tasks that can be
        // queued)
        taskExecutor.setQueueCapacity(150);

        // Set the maximum pool size to 4 (maximum number of threads to allow in the
        // pool)
        taskExecutor.setMaxPoolSize(4);

        // Set a prefix for the names of threads created by this executor
        taskExecutor.setThreadNamePrefix("AsyncTaskThread-");

        // Initialize the ThreadPoolTaskExecutor
        taskExecutor.initialize();

        // Return the configured ThreadPoolTaskExecutor as an Executor bean
        return taskExecutor;
    }
}