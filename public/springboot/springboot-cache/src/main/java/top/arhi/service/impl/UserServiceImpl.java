package top.arhi.service.impl;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.transcoders.Transcoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.arhi.config.XMemcachedProperties;
import top.arhi.domain.User;
import top.arhi.mapper.UserMapper;
import top.arhi.service.UserService;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;


    @Autowired
    private XMemcachedProperties xMemcachedProperties;


    private HashMap<Long, User> cache = new HashMap<>();


    @Override
    public User findByIdWithOutCache(Long id) {
        return userMapper.findById(id);
    }


    @Override
    public User findById_hashMap(Long id) {
        //如果当前缓存中没有本次要查询的数据，则进行查询，否则直接从缓存中获取数据返回
        User user = cache.get(id);
        if(user == null){
            user = userMapper.findById(id);
            cache.put(id,user);
        }
        return user;
    }


    @Override
    @Cacheable(value="cacheSpace_user_",key="#id")
    public User findById_ecache(Long id) {
        return userMapper.findById(id);
    }

    @Override
    @Cacheable(value="cacheSpace_user_",key="#id")
    public User findById_redis(Long id) {
        return userMapper.findById(id);
    }


    @Override
    public User findById_memcache(Long id) {
        User user=null;
        try {
            user = (User)memcachedClient.get(xMemcachedProperties.getPrefix() + "user" + id);
            if(user==null){
                memcachedClient.set(xMemcachedProperties.getPrefix()+"book"+id,5,userMapper.findById(id),new SerializingTranscoder());
                user=userMapper.findById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
