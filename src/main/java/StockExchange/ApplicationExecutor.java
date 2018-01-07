package StockExchange;

import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.*;

public class ApplicationExecutor {
    private static ApplicationExecutor instance;
    private ThreadPoolExecutor backgroundThreadPool;
    private BlockingQueue<Runnable> blockingQueue;

    private ApplicationExecutor() {
        blockingQueue = new ArrayBlockingQueue<>(50);
        this.backgroundThreadPool = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS, blockingQueue);
    }

    public static ApplicationExecutor getInstance() {
        if(instance == null) {
            instance = new ApplicationExecutor();
        }
        return instance;
    }

    public ThreadPoolExecutor getBackgroundThreadPool() {
        return backgroundThreadPool;
    }

}
