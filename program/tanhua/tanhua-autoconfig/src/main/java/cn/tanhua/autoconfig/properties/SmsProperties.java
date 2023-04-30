package cn.tanhua.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "tanhua.sms")
@Data
public class SmsProperties {
    private String url;
    private String account;
    private String password;
}
