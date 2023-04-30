package cn.fiesacyum.config;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value="cn.fiesacyum",
        excludeFilters = {@ComponentScan.Filter(classes={Controller.class})} )
@PropertySource("classpath:jdbc.properties")
@Import({JDBCConfig.class,MyBatisConfig.class})
@EnableTransactionManagement
public class SpringConfig {

    @Bean("transactionManager")
    public PlatformTransactionManager getPlatformTransactionManager(@Autowired DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}*/
