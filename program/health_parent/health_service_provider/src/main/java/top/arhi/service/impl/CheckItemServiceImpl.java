package top.arhi.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.arhi.dao.CheckItemDao;
import top.arhi.entity.PageResult;
import top.arhi.pojo.CheckItem;
import top.arhi.service.CheckItemService;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
  	//新增
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //数据拦截
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id){
        //查询当前检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
           throw new RuntimeException("当前检查项被引用不能删除");
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        CheckItem checkItem = checkItemDao.findById(id);
        if(checkItem==null){
            throw new RuntimeException("记录不存在");
        }
        return checkItem;
    }

    @Override
    public void edit(CheckItem checkItem) {
       try{
           checkItemDao.edit(checkItem);
       }catch(Exception e){
           throw new RuntimeException("更新失败");
       }

    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }


}