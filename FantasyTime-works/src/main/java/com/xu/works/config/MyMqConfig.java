package com.xu.works.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/14-下午 03:56
 */
@Configuration
public class MyMqConfig {

    // 创建交换机 作品更新通知
    @Bean
    public Exchange worksUpdateExchange(){
        Exchange exchange =new TopicExchange("works-update-exchange",true,false);
        return exchange;
    }

    @Bean
    public Queue worksUpdateQueue(){
//        HashMap<String, Object> arguments = new HashMap<>();
//        arguments.put("x-dead-letter-exchange","order-event-exchange");
//        arguments.put("x-dead-letter-routing-key","order.release.order");
//        arguments.put("x-message-ttl",180000);
        return new Queue("works.update.queue",true,false,false);
    }





    @Bean
    public Binding worksUpdateBinding(){
        return new Binding("works.update.queue",
                Binding.DestinationType.QUEUE,
                "works-update-exchange",
                "works.update",
                null);
    }


    @Bean
    ApplicationRunner runner(ConnectionFactory cf) {
        return args -> cf.createConnection().close();
    }
}
