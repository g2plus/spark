package cn.tanhua;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OSSTest {
    @Test
    public void testOSS() throws FileNotFoundException {

        String filePath = "C:\\Users\\arhi\\Downloads\\bf096b63f6246b604288904c2d92074a500fa2f0.jpeg";
        String fileName = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + UUID.randomUUID().toString().replace("-", "") + filePath.substring(filePath.lastIndexOf("."));
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tNjNJUb1zms8B8UX6Ri";
        String accessKeySecret = "Fn0SRuVaDVZ7CsP0WSM1CoxYQvm3zX";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写网络流地址。
        InputStream inputStream = new FileInputStream(new File(filePath));
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("fiesacyum", fileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
