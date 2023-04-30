package cn.tanhua.autoconfig.template;

import cn.tanhua.autoconfig.properties.AipFaceProperties;
import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

public class AipFaceTemplate {

    private AipFaceProperties aipFaceProperties;

    public AipFaceTemplate(AipFaceProperties aipFaceProperties) {
        this.aipFaceProperties=aipFaceProperties;
    }

    public boolean aipFace(String URL){
        // 初始化一个AipFace
        AipFace client = new AipFace(aipFaceProperties.getAPP_ID(), aipFaceProperties.getAPI_KEY(), aipFaceProperties.getSECRET_KEY());
        //aipFaceProperties

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);


        // 调用接口
        //error_code的值为零说明是人脸
        String image = URL;
        String imageType = "URL";

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);
        //判断错误码是否为0，是0说明图片带有人脸
        return 0==(Integer)res.get("error_code");
    }
}
