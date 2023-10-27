package ps.demo.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import ps.demo.entity.Product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncService {

    @Async("taskExecutor")
    @SneakyThrows
    public CompletableFuture<String> asyncronizedCall(Long sleep) {
        log.info("AsyncService to sleep = {} seconds", sleep);
        Thread.sleep(sleep*1000);
        String random = RandomStringUtils.randomAlphanumeric(25);
        log.info("AsyncService return the random string={}, after sleep={}", random, sleep);
        //return AsyncResult.forValue(random);
        return CompletableFuture.completedFuture(random);
    }

}
