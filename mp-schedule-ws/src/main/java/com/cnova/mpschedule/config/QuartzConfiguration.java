package com.cnova.mpschedule.config;

import com.cnova.mpschedule.core.factory.AutowiringSpringBeanJobFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration 
public class QuartzConfiguration {

    @Value("${org.quartz.jobStore.class}")
    public String jobStoreClass;
    @Value("${org.quartz.jobStore.mongoUri}")
    public String mongoUri;
    @Value("${org.quartz.jobStore.dbName}")
    public String dbName;
    @Value("${org.quartz.jobStore.collectionPrefix}")
    public String collectionPrefix;
    @Value("${org.quartz.jobStore.jobDataAsBase64}")
    private String jobDataAsBase64;
    @Value("${org.quartz.threadPool.threadCount}")
    public String threadCount;

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {

        AutowiringSpringBeanJobFactory sampleJobFactory = new AutowiringSpringBeanJobFactory();
        sampleJobFactory.setApplicationContext(applicationContext);
        return sampleJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory(applicationContext));

        /*Properties quartzProperties = new Properties();

        quartzProperties.setProperty("org.quartz.jobStore.class", this.jobStoreClass);
        if(this.jobStoreClass.contains("MongoDBJobStore")) {
            quartzProperties.setProperty("org.quartz.jobStore.mongoUri", this.mongoUri);
            quartzProperties.setProperty("org.quartz.jobStore.dbName", this.dbName);
            quartzProperties.setProperty("org.quartz.jobStore.collectionPrefix", this.collectionPrefix);
            quartzProperties.setProperty("org.quartz.jobStore.jobDataAsBase64", this.jobDataAsBase64);
            quartzProperties.setProperty("org.quartz.threadPool.threadCount", this.threadCount);
        }
        factory.setQuartzProperties(quartzProperties);*/

        return factory;
    }

    /*
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("jobone");
        obj.setTargetMethod("myTask");
        return obj;
    }
    //Job  is scheduled for 3+1 times with the interval of 30 seconds
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
        SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
        stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setRepeatInterval(30000);
        stFactory.setRepeatCount(3);
        return stFactory;
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(MyJobTwo.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "RAM");
        map.put(MyJobTwo.COUNT, 1);
        factory.setJobDataAsMap(map);
        factory.setGroup("mygroup");
        factory.setName("myjob");
        return factory;
    }
    
    //Job is scheduled after every 1 minute 
    @Bean("DezSecTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
        return stFactory;
    }
    */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        
//        Trigger simpleTrigger = simpleTriggerFactoryBean().getObject();
//        Trigger cronTrigger = cronTriggerFactoryBean().getObject();
        
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        //scheduler.setTriggers(simpleTrigger,cronTrigger);
        return scheduler;
    }
} 