package cn.tanhua;

import cn.tanhua.server.ServerApplication;
import cn.tanhua.autoconfig.template.HuanXinTemplate;
import cn.tanhua.commons.utils.Constants;
import cn.tanhua.dubbo.api.UserApi;
import cn.tanhua.model.pojo.User;
import cn.tanhua.server.ServerApplication;
import cn.tanhua.server.service.HuanXinService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class HuanxinTest {

    @Autowired
    private HuanXinTemplate huanXinTemplate;

    @DubboReference
    private UserApi userApi;

    @Test
    public void testCreateUser() {
        huanXinTemplate.createUser("hx106", "123456");
    }

    @Test
    public void register() {
        List<User> allUser = userApi.findAllUser();
        String hxUser = new String();
        for (User user : allUser) {
            hxUser = "hx" + user.getId();
            Boolean flag = huanXinTemplate.createUser(hxUser, Constants.INIT_PASSWORD);
            if (flag) {
                user.setHxUser(hxUser);
                user.setHxPassword(Constants.INIT_PASSWORD);
                userApi.update(user);
            }

        }
    }
}
