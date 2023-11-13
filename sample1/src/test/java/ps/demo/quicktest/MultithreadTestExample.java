package ps.demo.quicktest;
import com.google.common.util.concurrent.RateLimiter;
import lombok.*;
import org.apache.commons.lang3.RandomUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadTestExample {

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArgumentAndResult implements Serializable {
        private int argument;
        private int result;
        public long timeCost;
    }

    static RateLimiter rateLimiter = RateLimiter.create(1d); //1 qps

    @SneakyThrows
    public static void doWork(ArgumentAndResult argumentAndResult) {
        //rateLimiter.acquire();
        long start = System.currentTimeMillis();
        int parameter = argumentAndResult.getArgument();
        // Some computation
        System.out.println("doWork num="+parameter);
        Thread.sleep(RandomUtils.nextInt(10, 500));

        argumentAndResult.setResult(parameter * parameter);
        long timeTaken = System.currentTimeMillis() - start;
        argumentAndResult.setTimeCost(timeTaken);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<ArgumentAndResult> list = new ArrayList<>();

        for(int i=0; i < 1000; i++) {
            int num = i;
            ArgumentAndResult argumentAndResult = new ArgumentAndResult();
            list.add(argumentAndResult);
            argumentAndResult.setArgument(num);
            executor.submit(() -> {
                doWork(argumentAndResult);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.sort(list, new Comparator<ArgumentAndResult>() {
            @Override
            public int compare(ArgumentAndResult o1, ArgumentAndResult o2) {
                return Long.compare(o2.getTimeCost(), o1.getTimeCost());
            }
        });
        for (ArgumentAndResult argumentAndResult : list) {
            System.out.println("argumentAndResult = " + argumentAndResult);
        }
    }
}
