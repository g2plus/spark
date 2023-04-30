package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.service.WmChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //事务注解
/**
 *@Transactional 属性 rollbackFor 设置错误。
 * rollbackFor 可以指定能够触发事务回滚的异常类型。
 * Spring 默认抛出了未检查 unchecked 异常(继承自 RuntimeException 的异常)或者 Error 才回滚事务；
 * 其他异常不会触发回滚事务。如果在事务中抛出其他类型的异常，但却期望 Spring 能够回滚事务，就需要指定 rollbackFor 属性
 * 作者：日常更新
 * 链接：https://www.jianshu.com/p/907a895587bf
 * 来源：简书
 */
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {

    /**
     * list方法用来获取所有的信息.
     * @return
     */
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(this.list());
    }
}
