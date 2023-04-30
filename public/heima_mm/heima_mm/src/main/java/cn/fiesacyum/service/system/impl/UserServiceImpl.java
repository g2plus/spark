package cn.fiesacyum.service.system.impl;

import cn.fiesacyum.dao.system.ModuleDao;
import cn.fiesacyum.dao.system.UserDao;
import cn.fiesacyum.domain.system.Module;
import cn.fiesacyum.domain.system.User;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.system.UserService;
import cn.fiesacyum.utils.MD5Util;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        SqlSession sqlSession=null;
        try{
            //获取sqlSession会话
            sqlSession = MapperFactory.getSqlSession();
            //创建接口的实现类对象
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            //设置用户的id并保存user
            user.setId(UUID.randomUUID().toString());
            //使用MD5加密算法对密码进行加密，提高账号的安全性
            user.setPassword(MD5Util.md5(user.getPassword()));
            mapper.save(user);
            //事务提交保存到数据库
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务，失败回滚
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            //更新用户信息的考虑，有些信息应该不能被修改，如何解决问题?
            //方案一：从数据库取出信息，然后对原有信息进行覆盖
            //方案二：限定修改项，前端对不可修改信息进行显示，但是不能更改，后端限定更新项目
            //备注：本案例采用方案二。
            mapper.update(user);
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            mapper.delete(user);
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.rollback(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession=null;
        User user=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            user=mapper.findById(id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession=null;
        List<User> list=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            list=mapper.findAll();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
               TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public PageInfo findAll(int pageNum,int pageSize){
        SqlSession sqlSession=null;
        PageInfo pageInfo=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            Page page= PageHelper.startPage(pageNum, pageSize);
            List<User> all = mapper.findAll();
            pageInfo=new PageInfo(all);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return pageInfo;
    }

    @Override
    public void updateRole(String userId, String[] roleIds) {
        SqlSession sqlSession=null;
        try{
            sqlSession = MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            mapper.deleteRole(userId);
            //如果取消扮演角色，roleIds为null，添加if条件判断，避免抛出NullPointException的问题。
            if(roleIds!=null){
                for (String roleId : roleIds) {
                    mapper.saveRole(userId,roleId);
                }
            }
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.rollback(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public User login(String email, String password) {
        SqlSession sqlSession=null;
        User user=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            UserDao mapper = MapperFactory.getMapper(sqlSession, UserDao.class);
            password=MD5Util.md5(password);
            user=mapper.findByEmailAndPwd(email,password);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<Module> findModuleById(String id) {
        //TODO Module模块查询
        SqlSession sqlSession=null;
        List<Module> moduleList=null;
        try{
            sqlSession=MapperFactory.getSqlSession();
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            moduleList = mapper.findModuleByUserId(id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return moduleList;
    }
}
