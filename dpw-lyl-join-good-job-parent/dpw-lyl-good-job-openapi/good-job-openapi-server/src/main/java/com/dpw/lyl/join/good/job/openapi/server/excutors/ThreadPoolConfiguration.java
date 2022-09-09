package com.dpw.lyl.join.good.job.openapi.server.excutors;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfiguration {

    /**
     * 核心线程数
     */
    @Value("${thread.pool.coreSize:}")
    private  int CORE_SIZE ;

    /**
     * 最大线程数
     */
    @Value("${thread.pool.maxSize:}")
    private  int MAX_SIZE;

    /**
     * 阻塞队列长度
     */
    @Value("${thread.pool.queueCapacity:}")
    private  int QUEUE_CAPACITY ;

    /**
     * 线程名称
     */
    @Value("${thread.pool.threadNamePrefixNettyServer:}")
    private  String THREAD_NAME_PREFIX_NETTY_SERVER;

    /**
     * 线程名称
     */
    @Value("${thread.pool.threadNamePrefixNettyClient:}")
    private String THREAD_NAME_PREFIX_NETTY_CLIENT ;

    @Bean(name = "nettyServerPoolTaskExecutor")
    public ThreadPoolTaskExecutor nettyServerPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX_NETTY_SERVER);
        threadPoolTaskExecutor.setThreadFactory(Executors.defaultThreadFactory());
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setRejectedExecutionHandler((runnable, executor) -> {
            //设置拒绝策略
        });
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setTaskDecorator(runnable -> {
            Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
            return () -> {
                try {
                    if (null != copyOfContextMap) {
                        MDC.setContextMap(copyOfContextMap);
                    }
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            };
        });
        return threadPoolTaskExecutor;
    }

    @Bean(name = "nettyClientThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor nettyClientThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX_NETTY_CLIENT);
        threadPoolTaskExecutor.setThreadFactory(Executors.defaultThreadFactory());
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setRejectedExecutionHandler((runnable, executor) -> {
            //设置拒绝策略
        });
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setTaskDecorator(runnable -> {
            Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
            return () -> {
                try {
                    if (null != copyOfContextMap) {
                        MDC.setContextMap(copyOfContextMap);
                    }
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            };
        });
        return threadPoolTaskExecutor;
    }
    /**
     * @author: dengpw
     * @createTime: 2022年08月17 15:24:10
     * @description: 配置全局http请求工具restTemplate
     * @param:
     * @return: org.springframework.web.client.RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
