package com.springcloud.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value="/log")
@RefreshScope
public class LogReceiveController
{
    @Value("${user.name}")
    private String dataSourceIp;

    @Value("${user.age}")
    private String dataSourcePort;


    @GetMapping("/operLog")
    @SentinelResource(value = "log_recelive",fallback = "fallback",blockHandler = "blockHandler")
    public String operLogReceive()
    {
        return "连接数据库"+dataSourceIp+dataSourcePort;
    }

    public String blockHandler(BlockException exception)
    {
        return "服务降级";
    }

    public String fallback()
    {
        return "内部错误";
    }

    @GetMapping("/queryList")
    public String queryList()
    {
        List<String> messageList = SequenceMessageCustomer.messageList;
        String s = JSON.toJSONString(messageList);
        return s;
    }
}
