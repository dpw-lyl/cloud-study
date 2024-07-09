package com.dpw.lyl.join.good.job.foundation.utils.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author Administrator
 */
@Slf4j
public class ThreadPoolUtil {

    /**
     * 优雅地关闭线程池，确保所有已提交的任务完成后再释放资源。
     *
     * @param executorService 线程池实例
     * @param timeout         任务完成的等待超时时间
     * @param unit            超时时间单位
     */
    public static void shutdownGracefully(ExecutorService executorService, long timeout, TimeUnit unit) {
        if (executorService != null && !executorService.isShutdown()) {
            // 关闭线程池，不再接受新任务
            executorService.shutdown();

            try {
                // 等待所有已提交的任务完成
                if (!executorService.awaitTermination(timeout, unit)) {
                    // 如果超时，则强制关闭线程池
                    executorService.shutdownNow();

                    // 如果仍存在未完成的任务，再等待一次
                    if (!executorService.awaitTermination(timeout, unit)) {
                        log.error("线程池没有在指定时间内终止");
                    }
                }
            } catch (InterruptedException e) {
                // 如果线程被中断，强制关闭线程池
                executorService.shutdownNow();

                // 重新抛出中断异常
                Thread.currentThread().interrupt();
            }
        }
    }


    private static ExecutorService createThreadPoolWithExceptionHandler() {
        RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("任务被拒绝：" + r.toString());
            }
        };

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("MyPool-%d")
                .setUncaughtExceptionHandler((t, e) -> System.err.println("线程" + t.getName() + "中发生异常：" + e.getMessage()))
                .build();

        return new ThreadPoolExecutor(
                2, // 核心线程数
                5, // 最大线程数
                60L, // 空闲线程存活时间
                TimeUnit.SECONDS, // 时间单位
                new LinkedBlockingQueue<>(10), // 任务队列
                threadFactory, // 线程工厂
                rejectionHandler // 任务拒绝策略
        );
    }

    private static void handleFutureException(Future<?> future) {
        try {
            future.get(); // 这里会阻塞直到任务完成或抛出异常
        } catch (Exception e) {
            // 处理从 Future 获取结果时抛出的异常
            System.err.println("Future 异常：" + e.getMessage());
        }
    }

    public static <T> void batchTask(List<T> tasks, Executor executor, Consumer<? super T> consumer) throws InterruptedException {

        if (null == tasks || tasks.isEmpty()) {
            return;
        }

        if (Objects.isNull(executor)) {
            return;
        }

        CountDownLatch countDownLatch = new CountDownLatch(tasks.size());

        for (T task : tasks) {
            executor.execute(() -> {

                try {
                    consumer.accept(task);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

    }


    // 示例使用方法
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 提交一些任务...

        // 优雅地关闭线程池
        shutdownGracefully(executor, 5, TimeUnit.SECONDS);
    }
}