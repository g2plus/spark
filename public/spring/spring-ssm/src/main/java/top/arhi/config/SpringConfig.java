package top.arhi.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "top.arhi",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
)
@Import({JdbcConfig.class,MybatisConfig.class})
@EnableAspectJAutoProxy
public class SpringConfig {
}
