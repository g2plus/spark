package cn.tanhua.server.service;

import cn.tanhua.dubbo.api.UserLocationApi;
import cn.tanhua.model.vo.ErrorResult;
import cn.tanhua.server.exception.BusinessException;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class BaiduService {

    @DubboReference
    private UserLocationApi userLocationApi;


    public void updateLocation(Long userId,Double longitude,Double latitude, String address) {
        Boolean flag = userLocationApi.updateLocation(userId,longitude,latitude,address);
        if(!flag){
            throw new BusinessException(ErrorResult.error());
        }
    }
}
