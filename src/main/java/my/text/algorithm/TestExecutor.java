package my.text.algorithm;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Title: TestExecutor
 * @Description:
 * @Author deepexi-raobinghua
 * @Date 2022/12/7 16:36
 * @Version 1.0
 */
public class TestExecutor {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory());
        dynamicModifyExecutor(threadPoolExecutor);
    }


    private static void dynamicModifyExecutor(ThreadPoolExecutor executor) throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus(executor, "—————改变之前");
        executor.setCorePoolSize(2);
        executor.setMaximumPoolSize(2);
        threadPoolStatus(executor, "—————改变之后");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++" + new Date());
        Thread.currentThread().join(10*1000);
        System.out.println("--------------------------------------------"+ new Date());
        executor.shutdown();
    }

    private static void threadPoolStatus(ThreadPoolExecutor executor, String name){
        StringBuilder sb = new StringBuilder(name + "-");
        sb.append(Thread.currentThread().getName() + ":");
        sb.append(", 核心线程数:" + executor.getCorePoolSize());
        sb.append(", 最大线程数:" + executor.getMaximumPoolSize());
        sb.append(", 实际线程数:" + executor.getPoolSize());
        sb.append(", 活跃线程数:" + executor.getActiveCount());
        sb.append(", 线程活跃度:" + String.format("%1.2f%%",
                Double.parseDouble(executor.getActiveCount()+"") / Double.parseDouble(executor.getMaximumPoolSize()+"")* 100));
        sb.append(", 排队任务数:" + executor.getQueue().size());
        sb.append(", 完成任务数:" + executor.getCompletedTaskCount());
        sb.append(", 队列剩余数:" + executor.getQueue().remainingCapacity());
        sb.append(", 队列占用率:" + String.format("%1.2f%%",
                Double.parseDouble(executor.getQueue().size()+"") / Double.parseDouble(executor.getQueue().size() + executor.getQueue().remainingCapacity()+"")* 100));
        System.out.println(sb.toString());
    }

}