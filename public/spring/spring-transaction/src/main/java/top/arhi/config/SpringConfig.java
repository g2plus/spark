package top.arhi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({JdbcConfig.class,MybatisConfig.class})
@ComponentScan("top.arhi")
/***
 * 开启事务管理
 */
@EnableTransactionManagement
public class SpringConfig {
}
