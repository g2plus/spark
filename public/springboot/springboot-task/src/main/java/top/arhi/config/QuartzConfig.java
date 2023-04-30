package top.arhi.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.arhi.quartz.ChargeJob;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail printJobDetail(){
        //绑定具体的工作
        return JobBuilder
                .newJob(ChargeJob.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger printJobTrigger(){
        ScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        //绑定对应的工作明细
        return TriggerBuilder.newTrigger().forJob(printJobDetail()).withSchedule(schedBuilder).build();
    }
}
