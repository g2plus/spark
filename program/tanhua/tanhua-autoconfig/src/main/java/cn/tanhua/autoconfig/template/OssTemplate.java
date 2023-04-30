package cn.tanhua.autoconfig.template;

import cn.tanhua.autoconfig.properties.OssProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OssTemplate {

    private OssProperties ossProperties;

    public OssTemplate(OssProperties ossProperties) {
        this.ossProperties=ossProperties;
    }

    public String upLoad(InputStream inputStream, String originalFilename){

        String fileName=new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/"+
                UUID.randomUUID().toString().replace("-","")
                +originalFilename.substring(originalFilename.lastIndexOf("."));
        String bucketDomain = ossProperties.getDomainName();
        OSS ossClient = new OSSClientBuilder().
                build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(),
                        ossProperties.getAccessKeySecret());
        ossClient.putObject(ossProperties.getBucketName(), fileName, inputStream);
        ossClient.shutdown();
        return bucketDomain+"/"+fileName;
    }

}
