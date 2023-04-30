package cn.tanhua.autoconfig;

import cn.tanhua.autoconfig.properties.AipFaceProperties;
import cn.tanhua.autoconfig.properties.HuanXinProperties;
import cn.tanhua.autoconfig.properties.OssProperties;
import cn.tanhua.autoconfig.properties.SmsProperties;
import cn.tanhua.autoconfig.template.AipFaceTemplate;
import cn.tanhua.autoconfig.template.HuanXinTemplate;
import cn.tanhua.autoconfig.template.OssTemplate;
import cn.tanhua.autoconfig.template.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties({
        SmsProperties.class,
        OssProperties.class,
        AipFaceProperties.class,
        HuanXinProperties.class
})
public class TanhuaAutoConfiguration {
    @Bean
    public SmsTemplate smsTemplate(SmsProperties smsProperties){
        return new SmsTemplate(smsProperties);
    }

    @Bean
    public OssTemplate ossTemplate(OssProperties ossProperties){
        return new OssTemplate(ossProperties);
    }

    @Bean
    public AipFaceTemplate aipFaceTemplate(AipFaceProperties aipFaceProperties){
        return new AipFaceTemplate(aipFaceProperties);
    }

    @Bean
    public HuanXinTemplate huanXinTemplate(HuanXinProperties huanXinProperties){
        return new HuanXinTemplate(huanXinProperties);
    }
}
