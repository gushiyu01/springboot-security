package com.gsy.security;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MessageEntity implements Delayed {
    private int id;
    private String body; // 消息内容
    private long executeTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public MessageEntity(int id, String body, long delayTime) {
        this.id = id;
        this.body = body;
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    // 自定义实现比较方法返回 1 0 -1三个参数
    @Override
    public int compareTo(Delayed delayed) {
        MessageEntity msg = (MessageEntity) delayed;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
                : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
}
