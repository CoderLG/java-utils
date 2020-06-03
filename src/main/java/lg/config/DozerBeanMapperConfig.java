package lg.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: LG
 * date: 2019-10-29 11:13
 * desc:
 *
 * 将两个实体中相同的属性赋值
 */

@Configuration
public class DozerBeanMapperConfig {

    @Bean
    public DozerBeanMapper mapper(){
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        return dozerBeanMapper;
    }
}
