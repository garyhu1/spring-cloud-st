package com.garyhu.conf;

import com.garyhu.listener.MyRedisChannelListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.rmi.activation.UnknownObjectException;

/**
 * @author : Administrator
 * @since : 2018/11/5
 * @decripetion : Redis消息
 **/
@Configuration
public class RedisConf {

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     * @return
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MyRedisChannelListener());
        // 改用JDK序列化机制
//        adapter.setSerializer(new JdkSerializationRedisSerializer());
        return adapter;
    }

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，
     * 该消息监听器通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param factory
     * @param adapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory,
                                                   MessageListenerAdapter adapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);
        container.addMessageListener(adapter,new PatternTopic("news"));
        return container;
    }

    /**
     * 自定义序列化策略
     *
     * 设置默认的RedisTemplate的key的序列化策略为StringRedisSerializer
     */
    @Bean("strKeyRedisTemplate")
    public RedisTemplate<Object,Object> strKeyRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        return template;
    }

    /**
     * 自定义JSON的序列化方式
     * 以下代码是使用jackson作为默认的序列化方式
     */
    @Bean("jsonRedisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)throws UnknownObjectException{
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

}
