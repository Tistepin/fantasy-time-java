package com.xu.works.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-02-2023/2/15-上午 08:38
 */
@Configuration
public class MyRabbitConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    /**
     * 定制 RabbitTemplate
     * 1.服务器收到消息就回调
     *      1.1 开启 publisher-confirm-type:  SIMPLE,CORRELATED,NONE
     *          NONE 是不开启
     *          CORRELATED 开启消息成功发送就回调
     *          SIMPLE值经测试有两种效果，其一效果和CORRELATED值一样会触发回调方法，
     *          其二在发布消息成功后使用rabbitTemplate调用waitForConfirms或waitForConfirmsOrDie方法
     *          等待broker节点返回发送结果，根据返回结果来判定下一步的逻辑，
     *          要注意的点是waitForConfirmsOrDie方法如果返回false则会关闭channel，
     *          则接下来无法发送消息到broker;
     *
     * 2.消息正确抵达队列
     *      2.1 配置开启
     *          # 开启消息成功被队列接收回调
     *          publisher-returns: true
     *          # 如果消息成功抵达队列 就以异步的方式优先回调
     *          template:
     *              mandatory: true
     *      2.2 设置确认回调setReturnCallback
     *
     * 3.消费端确认 (保证每一个消费端正确消费 此时才可以删除消息)
     *      3.1 默认是自动确认模式 消息接收就立即删除
     *          问题： 收到很多消息 现在模式是自动确认 只要消息被成功消费就自动删除 如何处理消息的过程中(逻辑代码运行中) 直接卡死宕机等 那么消息就丢失了
     *      3.2 使用手动模式 如果没有手动确认 那么就一直存在队列中
     *           channel.basicAck(deliveryTag,false); 手动确认
     *           channel.basicNack(deliveryTag,false,true);
     *           拒绝确认 第三个参数 true 重新回归队列被消费者消费 false则进入死信队列
     */
    @PostConstruct  // 初始化注解 类初始化后调用这个方法 消息成功被服务器接收回调
    public void initRabbitTemplate() {
        // 消息確認回調
        rabbitTemplate.setConfirmCallback(
                new RabbitTemplate.ConfirmCallback() {
                    /**
                     *
                     * @param correlationData 消息唯一关联数据 (唯一ID)
                     * @param ack 是否发送成功
                     * @param cause 错误原因
                     */
                    @Override
                    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                        /**
                         *  消息缺失解决
                         * 1.保存消息确认机制 发送确认和消费确认 全手动
                         * 2.每一个消息都在数据库做好记录 定期将失败的消息全部再次发送
                         */
                        System.out.println("confirm...CorrelationData"+correlationData);
                        System.out.println("confirm...ack"+ack);
                        System.out.println("confirm...cause"+cause);
                    }
                }
        );
        // 消息成功抵达队列回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 触发时机 正确抵达不会触发 错误抵达就会触发回调
             * @param message 消息的详细信息
             * @param replyCode 回复的状态码
             * @param replyText 回复的文本内容
             * @param exchange 哪个交换机给队列发的消息
             * @param routingKey 消息发送的时候的路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("Fail message{"+message+"]==>replyCode["+replyCode+"]==>replyText["+replyText+"]==>exchange["+exchange+"]==>routingKey["+routingKey+"]");
            }
        });
    }
}
