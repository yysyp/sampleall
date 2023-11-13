package ps.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ps.demo.common.MyBaseResponse;
import ps.demo.common.MyBaseController;
import ps.demo.common.MyStringDataResponse;
import ps.demo.entity.Product;
import ps.demo.service.AsyncService;
import ps.demo.service.CacheService;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController extends MyBaseController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private AsyncService asyncService;

    @Operation(summary = "Cache demo get")
    @GetMapping("/cache")
    public MyBaseResponse findProduct(@RequestParam(name = "productId") Long productId) {
        Product product = cacheService.findProductById(productId);
        return MyStringDataResponse.successWithData(product);
    }

    @Operation(summary = "Cache demo delete")
    @DeleteMapping("/cache")
    public MyBaseResponse deleteProduct(@RequestParam(name = "productId") Long productId) {
        cacheService.deleteProductFromCache(productId);
        return MyBaseResponse.success();
    }

    @SneakyThrows
    @Operation(summary = "Asynchronous demo")
    @GetMapping("/async")
    public MyBaseResponse asyncCall(@RequestParam(name = "sleepSeconds") Long sleepSeconds,
                                    @RequestParam(name = "wait") Boolean wait) {


        CompletableFuture<String> task1 = asyncService.asyncronizedCall(sleepSeconds);
        CompletableFuture<String> task2 = asyncService.asyncronizedCall(sleepSeconds);
        CompletableFuture<String> task3 = asyncService.asyncronizedCall(sleepSeconds);
        CompletableFuture<String> task4 = asyncService.asyncronizedCall(sleepSeconds);

        if (wait) {
            CompletableFuture.allOf(task1, task2, task3, task4).join();
            String result = task1.get();
            return MyStringDataResponse.successWithData(result);
        }
        // str.get();
        return MyStringDataResponse.successWithData("You don't wait.");
    }

}
