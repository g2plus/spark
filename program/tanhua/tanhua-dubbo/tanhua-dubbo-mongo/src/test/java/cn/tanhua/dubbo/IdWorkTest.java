package cn.tanhua.dubbo;

import cn.tanhua.dubbo.util.IdWorker;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DubboMongoApplication.class)
@RunWith(SpringRunner.class)
public class IdWorkTest {

    @Autowired
    private IdWorker idWorker;

    @Test
    public void test() {
        Long id = idWorker.getNextId("test");
        System.out.println(id);
    }
}
