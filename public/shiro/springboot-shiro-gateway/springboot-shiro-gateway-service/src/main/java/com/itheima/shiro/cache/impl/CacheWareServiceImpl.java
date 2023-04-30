package com.itheima.shiro.cache.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.itheima.shiro.cache.CacheWareService;
import com.itheima.shiro.pojo.CacheWare;
import com.itheima.shiro.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description：缓存仓库服务实现
 */
@Service("cacheWareService")
public class CacheWareServiceImpl implements CacheWareService {

    private Multimap<String, CacheWare> cacheWareMaps = ArrayListMultimap.create();

    /**
     * 数据锁
     */
    private static final ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void clearCacheWare() {
        try {
            reentrantLock.lock();
            cacheWareMaps.clear();
        } finally {
            reentrantLock.unlock();
        }
    }


    @Override
    public void createCacheWare(Multimap<String, CacheWare> CacheWareMap) {
        try {
            reentrantLock.lock();
            this.cacheWareMaps = CacheWareMap;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public CacheWare queryCacheWare(String serviceName, String methodName) {
        if (EmptyUtil.isNullOrEmpty(serviceName) || EmptyUtil.isNullOrEmpty(serviceName)) {
            return null;
        }
        StringBuffer serviceNameStringBuffer = new StringBuffer(serviceName);
        StringBuffer methodNameStringBuffer = new StringBuffer(methodName);
        String key = serviceNameStringBuffer.append(":").append(methodName).toString();
        Collection<CacheWare> cacheWares = cacheWareMaps.get(key);
        return EmptyUtil.isNullOrEmpty(cacheWares) ? null : cacheWares.iterator().next();
    }
}
