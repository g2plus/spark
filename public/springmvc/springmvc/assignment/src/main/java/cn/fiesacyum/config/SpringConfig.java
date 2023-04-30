package cn.fiesacyum.config;

import org.springframework.context.annotation.*;


@Configuration
@ComponentScan(value="cn.fiesacyum"/*,
        excludeFilters = {@ComponentScan.Filter(classes={Controller.class})}*/ )
@PropertySource("classpath:jdbc.properties")
@Import({JDBCConfig.class,MyBatisConfig.class})
public class SpringConfig {
}
