package com.rock.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created with IDEA
 * author:wjx
 * Date:2019/9/4
 * Time:13:43
 */
@Slf4j
public class FutureTaskExample1 {

    private static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("线程执行中");
            Thread.sleep(3000);
            return "完成";

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> result = service.submit(new MyCallable());
        String futureResult = result.get();
        log.info("main方法中执行代码");
        Thread.sleep(1000);
        log.info("线程执行结果为：{}",futureResult);

    }
}
