package ps.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心线程数
        executor.setMaxPoolSize(10); // 最大线程数
        executor.setQueueCapacity(25); // 队列容量
        executor.setKeepAliveSeconds(60); // 空闲线程的存活时间（秒）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("taskExecutor-"); // 线程名前缀
        executor.setWaitForTasksToCompleteOnShutdown(true); // 是否等待任务完成再关闭线程池
        executor.setAwaitTerminationSeconds(60); // 线程池关闭时等待任务结束的最长时间（秒）
        executor.initialize();
        return executor;
    }
}