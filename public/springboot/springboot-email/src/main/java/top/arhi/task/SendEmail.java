package top.arhi.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.arhi.service.SendMailService;

@Component
public class SendEmail {

    @Autowired
    private SendMailService sendMailService;

    @Scheduled(cron="0 35 18 * * ? ")
    public void execute(){
        sendMailService.sendMail();
    }

}
