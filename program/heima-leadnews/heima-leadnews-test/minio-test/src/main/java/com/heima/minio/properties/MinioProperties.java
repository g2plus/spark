package com.heima.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix="minio")
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String contentType;
    private String bucket;

}
