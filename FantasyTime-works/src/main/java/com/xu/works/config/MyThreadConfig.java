package com.xu.works.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/6-下午 04:27
 */
@Configuration
public class MyThreadConfig {
    /**
     * 1.继承Thread
     *          Tread01 tread01 = new Tread01();
     *          tread01.start();
     * 2.实现Runnable接口
     *          Runnable02 runnable02 = new Runnable02();
     *          runnable02.run();
     * 3.实现callable接口 +  FutureTask ( 拿到返回结果处理异常)
     *         FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable01());
     *         new Thread(integerFutureTask).start();
     *         // 获取返回结果 是阻塞等待
     *         Integer integer = integerFutureTask.get();
     *
     * 4.线程池
     *       给线程池提交任务
     *       2.创建线程池
     *          1.Executors.newFixedThreadPool(10);
     *          2.原生创建
     *
     *  异步
     *         Tread01 tread01 = new Tread01();
     *         new Thread(tread01).start();
     */

    /**
     * 4.线程池 性能稳定
     */
    /**
     * 七大参数
     * 1.corePoolSize 线程池创建好准备就绪的线程数量 等待接收异步任务执行 5 就是new 了五个Thread 只要线程池不销毁就会一直存在
     * 2.maximumPoolSize 最大线程数量
     * 3.keepAliveTime 如果当前线程数量大于 corePool数量 释放空闲的线程 如果该线程在该时间内没有获取新任务 就释放 释放的线程等于
     * 当前线程数量减去最大线程数 最大线程5 当前 7  7-5=2 就释放2个
     * 4.unit 时间单位
     * 5.workQueue 如果任务有很多 就会将目前多的任务会放在队列里面 只要有线程空闲了 就会去队列里面取出新的任务去执行
     *          new LinkedBlockingDeque<>() 默认是Integer最大值 可能会导致内存不够
     * 6.threadFactory 线程创建的工厂
     * 7.handler 如果队列满了 拒绝执行任务
     *
     * 工作顺序
     */
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties threadPoolConfigProperties){
        return new ThreadPoolExecutor(threadPoolConfigProperties.getCoreSize(),
                threadPoolConfigProperties.getMaxSize(),
                threadPoolConfigProperties.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

    }
}
