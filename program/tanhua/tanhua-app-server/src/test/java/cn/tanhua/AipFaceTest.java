package cn.tanhua;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.junit.Test;


import java.util.HashMap;

public class AipFaceTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "24021388";
    public static final String API_KEY = "ZnMTwoETXnu4OPIGwGAO2H4G";
    public static final String SECRET_KEY = "D4jXShyinv5q26bUS78xRKgNLnB9IfZh";

    @Test
    public void testAipFace() {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        //https://t1.huishahe.com/uploads/tu/202109/9999/968dec8b4f.jpg
        //https://tanhua001.oss-cn-beijing.aliyuncs.com/2021/04/19/a3824a45-70e3-4655-8106-a1e1be009a5e.jpg
        // 调用接口
        //error_code的值为零说明是人脸
        String image = "https://tanhua001.oss-cn-beijing.aliyuncs.com/2021/04/19/a3824a45-70e3-4655-8106-a1e1be009a5e.jpg";
        String imageType = "URL";

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(2));


    }
}
