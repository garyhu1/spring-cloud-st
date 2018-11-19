package com.garyhu.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author : Administrator
 * @since : 2018/11/4
 * @decripetion : 监听Redis（Pub/Sub）
 **/
public class MyRedisChannelListener implements MessageListener{
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] ch = message.getChannel();

        try{
            String msg = new String(body,"UTF-8");
            String channel = new String(ch,"UTF-8");

            System.out.println("get a message : "+msg+" from "+channel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
