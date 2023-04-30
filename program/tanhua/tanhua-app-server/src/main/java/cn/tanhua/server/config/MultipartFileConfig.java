package cn.tanhua.server.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultipartFileConfig {

    /**
     * 文件上传大小配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大大小
        factory.setMaxFileSize(DataSize.ofMegabytes(200));
        //设置总上传数据总大小
        factory.setMaxRequestSize((DataSize.ofMegabytes(500)));
        return factory.createMultipartConfig();
    }

}
