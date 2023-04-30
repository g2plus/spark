package top.arhi.autoconfig;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.arhi.properties.SmsProperties;
import top.arhi.template.SmsTemplate;

@EnableConfigurationProperties({
})
public class TanhuaAutoConfiguration {
    @Bean
    public SmsTemplate smsTemplate(SmsProperties smsProperties){
        return new SmsTemplate(smsProperties);
    }
}
