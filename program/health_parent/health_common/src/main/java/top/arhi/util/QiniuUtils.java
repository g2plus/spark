package top.arhi.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 七牛云工具类
 */
public class QiniuUtils {

    public static final String accessKey = "zVzkswa1VKTa_2l968Pq_1ZfrZYEbrj7cg2xzixh";
    public static final String secretKey = "cxzclupAeQLNH7xFxf4l0Os4hDCZG6FQhKeGyCL6";
    public static final String bucket = "a605288582";

    public static Logger log = LoggerFactory.getLogger(QiniuUtils.class);


    public static void upload2Qiniu(String filePath, String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            log.error(ex.getMessage());
        }
    }

    //上传文件
    public static void upload2Qiniu(byte[] bytes, String fileName) {
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        byte[] uploadBytes = bytes;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            log.error(ex.getMessage());
        }
    }

    //删除文件
    public static void deleteFileFromQiniu(String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            log.error(ex.getMessage());
        }
    }
    // TODO 阿里云文件存储工具类（要传递阿里云url完整地址获取，保存时后设置这个数据库的地址。
    // TODO 返回时保存完整的url路径）（保存的时候保存图片的url路径到img字段）
    // TODO 保存图片的base64编码到数据库（前端判断是否时base64编码，设置是否src的附加属性）
    // TODO 根据文件的hashCode值来决定存放到那个服务器里面
}
