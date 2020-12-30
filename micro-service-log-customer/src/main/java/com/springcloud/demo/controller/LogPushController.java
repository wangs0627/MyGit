package com.springcloud.demo.controller;

import com.springcloud.demo.log.LogCommonApi;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class LogPushController
{
    @Autowired
    private Source source;

    @Autowired
    private LogCommonApi logCommonApi;

    @GetMapping("/log/operLog/push")
    private String operLog()
    {
        return logCommonApi.operLogReceive();
    }

    @GetMapping("/pushLog")
    public String pushMessage()
    {
        Message message = MessageBuilder.withPayload("Hello,World"+String.valueOf(new Random().nextLong()))
                .setHeader(RocketMQHeaders.TAGS, "eim_source")
                .setHeader(RocketMQHeaders.TOPIC, "TopicTest")
                .setHeader(RocketMQHeaders.KEYS,"KEY")
                .build();
        this.source.output().send(message);
        return "success";
    }
}
