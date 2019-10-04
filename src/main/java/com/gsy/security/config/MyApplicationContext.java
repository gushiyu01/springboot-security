package com.gsy.security.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

@Service
public class MyApplicationContext extends ApplicationObjectSupport {

    private static ApplicationContext context;

    private static void setContext(ApplicationContext app){
        context = app;
    }

    private static ApplicationContext getContext(){
        return context;
    }

    public static Object getBean(String name){
        return getContext().getBean(name);
    }

    @Override
    protected void initApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> requiredType){
        return getBean(name, requiredType);
    }
}
