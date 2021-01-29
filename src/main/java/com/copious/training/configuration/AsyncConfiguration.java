package com.copious.training.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfiguration {

    @Value("${async.core.pool.size}")
    private int poolSize;
    @Value("${async.max.pool.size}")
    private int maxPoolSize;
    @Value("${async.core.queue.size}")
    private int queueCapacity;
    @Value("${async.task.completion.timeout}")
    private boolean taskCompletionTimeout;

    @Bean("taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(poolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(taskCompletionTimeout);
        threadPoolTaskExecutor.setThreadNamePrefix("TaskExecutor-1 :");
        return threadPoolTaskExecutor;
    }

}
