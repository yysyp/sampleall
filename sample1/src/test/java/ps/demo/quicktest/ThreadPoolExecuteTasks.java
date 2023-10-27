package ps.demo.quicktest;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecuteTasks {

    private static final int MAX_RETRY_COUNT = 3; // 最大重试次数

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 创建任务列表
        List<Callable<TaskResult>> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Wrong 2"));
        tasks.add(new Task("Task 3"));
        tasks.add(new Task("Task 4"));
        tasks.add(new Task("Task 5"));

        try {
            // 提交任务并获取Future对象列表
            List<Future<TaskResult>> futures = executorService.invokeAll(tasks);

            // 等待所有任务完成
            for (int i = 0, n = futures.size(); i < n; i++) {
                Future<TaskResult> future = futures.get(i);
                int retryCount = 0;
                TaskResult result = future.get();
                while (!result.flag && retryCount < MAX_RETRY_COUNT) {
                    Task failedTask = (Task) tasks.get(i);
                    retryCount++;
                    System.out.println("Task " + failedTask +" failed. Retrying... (Retry Count: " + retryCount + ")");
                    failedTask.setName("Task 2");
                    future = executorService.submit(failedTask);
                    result = future.get();
                }
                System.out.println("===>>Process result=" + result);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }

    static class TaskResult {
        TaskResult(Boolean flag, Object data) {
            this.flag = flag;
            this.data = data;
        }
        private Boolean flag;
        private Object data;

        public String toString() {
            return this.flag+"-"+this.data;
        }
    }

    static class Task implements Callable<TaskResult> {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        Task(String name) {
            this.name = name;
        }

        public String toString() {
            return "Task ["+this.name+"]";
        }

        @Override
        public TaskResult call() throws Exception {
            // 模拟任务执行，这里只是简单的输出任务名称
            System.out.println("Executing task: " + name);
            // 假设任务执行失败的条件是名称中包含"Task 2"
            return new TaskResult(!name.startsWith("Wrong"), name);
        }
    }
}
