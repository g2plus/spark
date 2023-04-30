package com.itheima.shiro.client;

import com.itheima.shiro.constant.CacheConstant;
import com.itheima.shiro.core.SimpleCacheService;
import com.itheima.shiro.core.base.ShiroUser;
import com.itheima.shiro.core.base.SimpleMapCache;
import com.itheima.shiro.core.base.SimpleToken;
import com.itheima.shiro.core.bridge.UserBridgeService;
import com.itheima.shiro.face.UserAdapterFace;
import com.itheima.shiro.utils.BeanConv;
import com.itheima.shiro.utils.EmptyUtil;
import com.itheima.shiro.utils.ShiroUserUtil;
import com.itheima.shiro.vo.ResourceVO;
import com.itheima.shiro.vo.RoleVO;
import com.itheima.shiro.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.util.ByteSource;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Description 权限桥接器
 */
@Slf4j
@Component("userBridgeService")
public class UserBridgeServiceImpl implements UserBridgeService {

    @Reference(version = "1.0.0")
    private UserAdapterFace userAdapterFace;

    @Autowired
    private SimpleCacheService simpleCacheService;

    @javax.annotation.Resource(name = "redissonClientForShiro")
    private RedissonClient redissonClient;

    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken,String realmName) {
        SimpleToken token = (SimpleToken)authcToken;
        UserVO user  = this.findUserByLoginName(token.getUsername());
        if(EmptyUtil.isNullOrEmpty(user)){
            throw new UnknownAccountException("账号不存在");
        }
        ShiroUser shiroUser = BeanConv.toBean(user, ShiroUser.class);
        String sessionId = ShiroUserUtil.getShiroSessionId();
        String cacheKeyResourcesIds = CacheConstant.RESOURCES_KEY_IDS+sessionId;
        shiroUser.setResourceIds(this.findResourcesIdsList(cacheKeyResourcesIds,user.getId()));
        String salt = user.getSalt();
        String password = user.getPassWord();
        return new SimpleAuthenticationInfo(shiroUser, password, ByteSource.Util.bytes(salt), realmName);
    }

    @Override
    public SimpleAuthorizationInfo getAuthorizationInfo(ShiroUser shiroUser) {
        UserVO user = BeanConv.toBean(shiroUser, UserVO.class);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String sessionId = ShiroUserUtil.getShiroSessionId();
        //查询用户拥有的角色
        String cacheKeyRole = CacheConstant.ROLE_KEY + sessionId;
        info.addRoles(this.findRoleList(cacheKeyRole, user.getId()));

        //查询用户拥有的资源
        String cacheKeyResources = CacheConstant.RESOURCES_KEY + sessionId;
        info.addStringPermissions(this.findResourcesList(cacheKeyResources, user.getId()));
        return info;
    }


    @Override
    public List<String> findRoleList(String cacheKeyRole, String userId) {
        List<RoleVO> roles = new ArrayList<RoleVO>();
        if (simpleCacheService.getCache(cacheKeyRole) != null) {
            roles = (List<RoleVO>) simpleCacheService.getCache(cacheKeyRole).get(cacheKeyRole);
        } else {
            roles = userAdapterFace.findRoleByUserId(userId);
            if (roles.size() > 0) {
                //用户角色存放到map
                Map<Object, Object> mapRole = new HashMap<Object, Object>();
                mapRole.put(cacheKeyRole, roles);
                //新建SimpleMapCache实例并放入缓存管理器
                SimpleMapCache cacheRole = new SimpleMapCache(cacheKeyRole, mapRole);
                simpleCacheService.creatCache(cacheKeyRole, cacheRole);
            }
        }
        List<String> rolesLabel = new ArrayList<String>();
        for (RoleVO role : roles) {
            rolesLabel.add(role.getLabel());
        }
        return rolesLabel;
    }


    @Override
    public List<String> findResourcesList(String cacheKeyResources,String userId) {
        List<ResourceVO> resourcesList = new ArrayList<ResourceVO>();
        if (simpleCacheService.getCache(cacheKeyResources) != null) {
            resourcesList = (List<ResourceVO>) simpleCacheService.getCache(cacheKeyResources).get(cacheKeyResources);
        } else {
            resourcesList = userAdapterFace.findResourceByUserId(userId);
            if (resourcesList.size() > 0) {
                //用户资源存放到map
                Map<Object, Object> mapResource = new HashMap<Object, Object>();
                mapResource.put(cacheKeyResources, resourcesList);
                //新建SimpleMapCache实例并放入缓存管理器
                SimpleMapCache cacheResource = new SimpleMapCache(cacheKeyResources, mapResource);
                simpleCacheService.creatCache(cacheKeyResources, cacheResource);
            }
        }
        List<String> resourcesLabel = new ArrayList<String>();
        for (ResourceVO resources : resourcesList) {
            resourcesLabel.add(resources.getLabel());
        }
        return resourcesLabel;
    }


    @Override
    public UserVO findUserByLoginName(String loginName) {
        String key = CacheConstant.FIND_USER_BY_LOGINNAME+loginName;
        RBucket<UserVO> rBucket = redissonClient.getBucket(key);
        UserVO user = rBucket.get();
        if (!EmptyUtil.isNullOrEmpty(user)) {
            return user;
        }else {
            user = userAdapterFace.findUserByLoginName(loginName);
            if (!EmptyUtil.isNullOrEmpty(user)) {
                rBucket.set(user, 300, TimeUnit.SECONDS);
                return user;
            }
        }
        rBucket.set(new UserVO(), 3, TimeUnit.SECONDS);
        return null;
    }

    @Override
    public List<String> findResourcesIdsList(String cacheKeyResources,String userId) {
        List<ResourceVO> resourcesList = new ArrayList<ResourceVO>();
        if (simpleCacheService.getCache(cacheKeyResources) != null) {
            resourcesList = (List<ResourceVO>) simpleCacheService.getCache(cacheKeyResources).get(cacheKeyResources);
        } else {
            resourcesList = userAdapterFace.findResourceByUserId(userId);
            if (resourcesList.size() > 0) {
                //用户资源存放到map
                Map<Object, Object> mapResource = new HashMap<Object, Object>();
                mapResource.put(cacheKeyResources, resourcesList);
                //新建SimpleMapCache实例并放入缓存管理器
                SimpleMapCache cacheResource = new SimpleMapCache(cacheKeyResources, mapResource);
                simpleCacheService.creatCache(cacheKeyResources, cacheResource);
            }
        }
        List<String> resourcesLabel = new ArrayList<String>();
        for (ResourceVO resources : resourcesList) {
            resourcesLabel.add(resources.getId());
        }
        return resourcesLabel;
    }

    @Override
    public void loadUserAuthorityToCache(ShiroUser user) {
        String sessionId = user.getSessionId();
        List<RoleVO> roles = userAdapterFace.findRoleByUserId(user.getId());
        //创建角色cachaeKey
        String cacheKeyRole = CacheConstant.ROLE_KEY + sessionId;
        //用户角色存放到map
        Map<Object, Object> mapRole = new HashMap<Object, Object>();
        mapRole.put(cacheKeyRole, roles);
        //新建SimpleMapCache实例并放入缓存管理器
        SimpleMapCache cacheRole = new SimpleMapCache(cacheKeyRole, mapRole);
        simpleCacheService.creatCache(cacheKeyRole, cacheRole);

        List<ResourceVO> resourcesList = userAdapterFace.findResourceByUserId(user.getId());
        if (resourcesList.size() > 0) {
            //创建资源cachaeKey
            String cacheKeyResources = CacheConstant.RESOURCES_KEY + sessionId;
            //用户资源存放到map
            Map<Object, Object> mapResource = new HashMap<Object, Object>();
            mapResource.put(cacheKeyResources, resourcesList);
            //新建SimpleMapCache实例并放入缓存管理器
            SimpleMapCache cacheResource = new SimpleMapCache(cacheKeyResources, mapResource);
            simpleCacheService.creatCache(cacheKeyResources, cacheResource);
        }
    }
}
