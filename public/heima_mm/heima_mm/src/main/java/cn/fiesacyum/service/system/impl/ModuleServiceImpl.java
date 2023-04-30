package cn.fiesacyum.service.system.impl;

import cn.fiesacyum.dao.system.ModuleDao;
import cn.fiesacyum.domain.system.Module;
import cn.fiesacyum.factory.MapperFactory;
import cn.fiesacyum.service.system.ModuleService;
import cn.fiesacyum.utils.TransactionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ModuleServiceImpl implements ModuleService {
    @Override
    public void save(Module module) {
        //事务的处理方式！！！
        SqlSession sqlSession = null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //保存
            //前端不进行id的设置，需要对module的id进行设置
            String id = UUID.randomUUID().toString();
            module.setId(id);
            mapper.save(module);
            //提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void delete(Module module) {
        SqlSession sqlSession = null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //删除
            mapper.delete(module);
            //提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public void update(Module module) {
        SqlSession sqlSession = null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //更新
            mapper.update(module);
            //提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            //提交事务失败，进行回滚事务
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
    }

    @Override
    public Module findById(String id) {
        SqlSession sqlSession = null;
        Module module=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            module=mapper.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return module;
    }

    @Override
    public List<Module> findAll() {
        SqlSession sqlSession = null;
        List<Module> list=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //查询
            list=mapper.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return list;
    }

    @Override
    public PageInfo findAll(int pageNum, int pageSize) {
        SqlSession sqlSession = null;
        PageInfo pageInfo=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //查询
            //拦截处理，确认pageSize与pageNum
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<Module> all = mapper.findAll();
            pageInfo = new PageInfo<>(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return pageInfo;
    }

    @Override
    public List<Map> findAuthorDataByRoleId(String roleId) {
        SqlSession sqlSession = null;
        List<Map> list=null;
        try {
            //获取到sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            //使用工具类（代理模式）来创建mapper对象
            ModuleDao mapper = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //查询
            list=mapper.findAuthorDataByRoleId(roleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //日志记录
        } finally {
            try {
                //释放资源
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                //释放资源失败，记录
                e.printStackTrace();
                //日志记录
            }
        }
        return list;

    }
}
