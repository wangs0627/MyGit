package com.springcloud.demo.controller;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class SequenceMessageCustomer
{
    /*DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("eim");*/
    public static List<String> messageList = new ArrayList<>();

    /*@PostConstruct
    public void init()
    {
        try
        {
            consumer.setNamesrvAddr("114.116.253.218:9876");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe("TopicTest", "eim_source");

            consumer.registerMessageListener(new MessageListenerOrderly() {


                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    context.setAutoCommit(true);
                    for (MessageExt msg : msgs) {
                        messageList.add(new String(msg.getBody()));
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer Started.");
        }
        catch (MQClientException e)
        {
            e.printStackTrace();
        }
    }*/

    /*@PreDestroy
    public void destory()
    {
        consumer.shutdown();
    }*/

    @StreamListener(value = Sink.INPUT, condition = "headers['TAGS']=='eim_source'")
    public void testCustomListener(Message message) {
        System.out.println(message.getHeaders().get("TAGS") + " " + message.getPayload().toString());
    }

}
