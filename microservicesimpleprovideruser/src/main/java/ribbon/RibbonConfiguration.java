package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: garyhu
 * @since: 2018/11/10 0010
 * @decription: 该类为Ribbon的配置类（自动以的会覆盖默认的配置，所以该类不应该在主应用程序的上下文@ComponentScan中或显示指定@ComponentScan不扫描该类）
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        // 负载规则改为随机
        return new RandomRule();
    }
}
