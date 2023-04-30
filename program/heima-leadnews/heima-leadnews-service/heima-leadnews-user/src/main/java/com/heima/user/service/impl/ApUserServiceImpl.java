package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.LoginDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Autowired
    private ApUserMapper apUserMapper;


    @Override
    public ApUser findByPhone(String phone) {
       return apUserMapper.findByPhone(phone);
    }

    @Override
    public ResponseResult findByPhoneNumber(LoginDto loginDto) {
        //查询数据库的记录
        if(StringUtils.isNotBlank(loginDto.getPhone())&&StringUtils.isNotBlank(loginDto.getPassword())){
           //根据用户输入的手机号判断是否存在此用户
           LambdaQueryWrapper<ApUser> wrapper = new LambdaQueryWrapper<ApUser>().eq(ApUser::getPhone, loginDto.getPhone());
            ApUser apUser = apUserMapper.selectOne(wrapper);
            if(apUser==null){
                //用户信息不存在,响应信息,用户不存在
                return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST,"用户不存在");
            }
            //进行密码的验证操作(从记录中取出salt值与用户输入的密码进行拼接,
            // 使用md5加密与记录中的记录进行对比,成功登录,返回token)
            String password = DigestUtils.md5DigestAsHex((loginDto.getPassword() + apUser.getSalt()).getBytes());
            if(apUser.getPassword().equals(password)){
                //如果验证成功,返回token
                Map<String, Object> map = new HashMap<>(16);
                map.put("token", AppJwtUtil.getToken(apUser.getId().longValue()));
                apUser.setSalt("");
                apUser.setPassword("");
                map.put("user", apUser);
                return ResponseResult.okResult(map);
            }else {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
        }else{
            //以游客的形式登录,返回token,id=OL
            Map<String, Object> map = new HashMap<>(16);
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
