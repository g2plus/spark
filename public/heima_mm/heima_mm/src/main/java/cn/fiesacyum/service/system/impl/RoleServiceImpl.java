package cn.fiesacyum.service.system.impl;

import cn.fiesacyum.dao.system.RoleDao;
import cn.fiesacyum.domain.system.Role;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.system.RoleService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {
    @Override
    public void save(Role role) {
        //事务的处理方式！！！
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //保存
            //前端不进行id的设置，需要对role的id进行设置
            String id= UUID.randomUUID().toString();
            role.setId(id);
            mapper.save(role);
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void delete(Role role) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //删除
            mapper.delete(role);
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void update(Role role) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //更新
            mapper.update(role);
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public Role findById(String id) {
        SqlSession sqlSession=null;
        Role role=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            role=mapper.findById(id);
        }catch(Exception e){
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        SqlSession sqlSession=null;
        List<Role> list=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //查询
            list=mapper.findAll();
        }catch(Exception e){
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return list;
    }

    @Override
    public PageInfo findAll(int pageNum, int pageSize){
        SqlSession sqlSession=null;
        PageInfo pageInfo=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //查询
            //拦截处理，确认pageSize与pageNum
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<Role> all = mapper.findAll();
            pageInfo=new PageInfo(all);
        }catch(Exception e){
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return pageInfo;
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //删除
            mapper.deleteRoleModule(roleId);
            if(moduleIds!=null){
                String[] moduleArray = moduleIds.split(",");
                for (String moduleId: moduleArray) {
                    mapper.saveRoleModule(roleId,moduleId);
                }
            }
            //提交事务
            TransactionUtil.commit(sqlSession);
        }catch(Exception e){
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public List<Role> findAllRoleByUserId(String userId) {
        SqlSession sqlSession=null;
        List<Role> all=null;
        try{
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            RoleDao mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //查询
            all = mapper.findAllRoleByUserId(userId);
        }catch(Exception e){
            throw new RuntimeException(e);
            //日志记录
        }finally{
            try{
                //释放资源
                TransactionUtil.close(sqlSession);
            }catch(Exception e){
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return all;
    }

}
