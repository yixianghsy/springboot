package hsy.com.logback.task;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

//@Configuration  
//public class ScheduleConfig implements SchedulingConfigurer {  
//  @Override  
//  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {  
//  
//      taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5)); //设置多个线程去执行  
//  }  
//}  