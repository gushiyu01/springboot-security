package com.gsy.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSecurityApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
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
        exec.awaitTermination(20, TimeUnit.SECONDS);
    }

    private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<>();

    public Connection getConnection(String key) throws Exception{

        FutureTask<Connection> connectionTask = connectionPool.get(key);

        if(connectionTask!=null){

            return connectionTask.get();

        }else{
            Callable<Connection> callable = () -> {
                // TODO Auto-generated method stub
                return createConnection();
            };
            FutureTask<Connection> newTask = new FutureTask<>(callable);
            connectionTask = connectionPool.putIfAbsent(key, newTask);
            if(connectionTask==null){
                connectionTask = newTask;
                connectionTask.run();
            }
            return connectionTask.get();
        }
    }

    //创建Connection
    private Connection createConnection(){
        return null;
    }

    @Test
    public void test2(){

        // 创建任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        // 创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            // 传入Callable对象创建FutureTask对象
            FutureTask<Integer> ft = new FutureTask<>(new ComputeTask(i, ""+i));
            taskList.add(ft);
            // 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务;
            exec.submit(ft);
        }

        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                //FutureTask的get方法会自动阻塞,直到获取计算结果为止
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("多任务计算后的总结果是:" + totalResult);

    }

    private class ComputeTask implements Callable<Integer> {

        private Integer result;
        private String taskName;

        public ComputeTask(Integer iniResult, String taskName){
            result = iniResult;
            this.taskName = taskName;
            System.out.println("生成子线程计算任务: "+taskName);
        }

        public String getTaskName(){
            return this.taskName;
        }

        @Override
        public Integer call() throws Exception {
            // TODO Auto-generated method stub

            for (int i = 0; i < 100; i++) {
                result =+ i;
            }
            // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
            Thread.sleep(5000);
            System.out.println("子线程计算任务: "+taskName+" 执行完成!");
            return result;
        }
    }

    @Test
    public void testCAS(){

    }

}
