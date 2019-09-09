package com.gsy.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSecurityApplicationTests {

    @Test
    public void contextLoads() {
        // 创建延时队列
        DelayQueue<MessageEntity> queue = new DelayQueue<MessageEntity>();
        // 添加延时消息,m1 延时3s
        MessageEntity m1 = new MessageEntity(1, "world", 3000);
        // 添加延时消息,m2 延时10s
        MessageEntity m2 = new MessageEntity(2, "hello", 10000);
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new Consumer(queue));
        exec.shutdown();
    }

}
