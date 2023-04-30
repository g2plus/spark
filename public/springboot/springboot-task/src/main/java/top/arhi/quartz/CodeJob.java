package top.arhi.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.arhi.util.DateUtils;
/**
 * spring-task
 * */
@Component
public class CodeJob {

    @Scheduled(cron = "0/1 * * * * ?")
    public void execute() {

        System.out.println("spring task is running... " + DateUtils.getTime());
    }

}
