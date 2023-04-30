package com.heima.test;

import com.heima.minio.config.RestTemplateConfig;
import com.heima.minio.properties.MinioProperties;
import com.heima.minio.test.MinioApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;

@SpringBootTest(classes= MinioApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
@Import(RestTemplateConfig.class)//引入RestTemplateConfig.class交给SpringContextAppplcation接管
public class MinioTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MinioProperties minioProperties;


    @Test
    public void testMinio(){
        try {
            FileInputStream inputStream = new FileInputStream("D:\\test.html");
            MinioClient minioClient = new MinioClient(minioProperties.getEndpoint(),minioProperties.getAccessKey(),minioProperties.getSecretKey());
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("test.html")
                    .contentType(minioProperties.getContentType())
                    .bucket(minioProperties.getBucket())
                    .stream(inputStream,inputStream.available(),-1)
                    .build();
            minioClient.putObject(putObjectArgs);
            if (restTemplate!=null){
                System.out.println("hello world!");
            }
        } catch (Exception e) {
            log.error("error{}",e.getMessage());
        }
    }
}
