package cn.tanhua.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="tanhua.face")
@Data
public class AipFaceProperties {
    /*
    APP_ID: 24021388
    API_KEY: ZnMTwoETXnu4OPIGwGAO2H4G
    SECRET_KEY: D4jXShyinv5q26bUS78xRKgNLnB9IfZh
    */
    private String APP_ID;
    private String API_KEY;
    private String SECRET_KEY;
}
