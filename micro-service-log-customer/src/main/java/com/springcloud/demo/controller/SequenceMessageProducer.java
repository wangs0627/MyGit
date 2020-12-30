package com.springcloud.demo.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

public class SequenceMessageProducer
{
    private static DefaultMQProducer producer = new DefaultMQProducer("eim");

    @PostConstruct
    public void init()
    {
        try
        {
            producer.setNamesrvAddr("114.116.253.218:9876");
            producer.setVipChannelEnabled(false);
            producer.start();
            System.out.println("Produceer Started.");
        }
        catch (MQClientException e)
        {
            e.printStackTrace();
        }
    }

    public static SendResult pushData(String message,Long orderId)
    {
        Message msg = new Message("TopicTest", "eim_source", "KEY", message.getBytes());

        SendResult sendResult = null;//订单id
        try
        {
            sendResult = producer.send(msg, new MessageQueueSelector()
            {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg)
                {
                    Long id = (Long) arg;  //根据订单id选择发送queue
                    long index = id % mqs.size();
                    return mqs.get((int) index);
                }
            }, orderId);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    @PreDestroy
    public void destory()
    {
        producer.shutdown();
        System.out.println("已关闭生产通道！");
    }
}
