package run.star.plan.thread.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * ThreadPool工具类
 * @author hecs
 * @date 2022/1/10 16:39
 */
@Slf4j
public class ThreadPool extends ThreadPoolExecutor {


    private static BlockingQueue<Runnable> queue      = new LinkedBlockingQueue<>(10000);

    private static int                     coreThread = Runtime.getRuntime().availableProcessors();

    private static ThreadPool              threadPool = new ThreadPool(coreThread, coreThread,
        10000L, TimeUnit.MILLISECONDS, queue, new ForceQueuePolicy());

    /**
     * 默认构造函数
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param handler
     */
    private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                       BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    /**
     * get ThreadPool Instance
     * @return
     */
    public static ThreadPool getInstance() {
        String blockingCoefficient = "0.5";
        try {
            int corePoolSize = (int) (Runtime.getRuntime().availableProcessors()
                                      / (1 - Double.parseDouble(blockingCoefficient)));
            if (corePoolSize != threadPool.getCorePoolSize()) {
                log.info("核心池大小调整大小,调整前:{},调整后:{}", threadPool.getCorePoolSize(), corePoolSize);
                threadPool.setCorePoolSize(corePoolSize);
                threadPool.setMaximumPoolSize(corePoolSize);
            }
        } catch (NumberFormatException e) {
            log.warn("阻塞系数:{} 错误，线程池核心池大小:{}", blockingCoefficient, threadPool.getCorePoolSize());
        }
        return threadPool;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (threadPool.getQueue().size() >= 800) {
//            LOGGER.info("Active: {}, Completed: {}, Task: {}, Queue:{}",
//                threadPool.getActiveCount(), threadPool.getCompletedTaskCount(),
//                threadPool.getTaskCount(), threadPool.getQueue().size());
        }
    }
}
