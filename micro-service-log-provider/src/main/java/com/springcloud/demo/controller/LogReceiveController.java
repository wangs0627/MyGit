package com.springcloud.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @SentinelResource("log_recelive")
    public String operLogReceive()
    {
        return "连接数据库"+dataSourceIp+dataSourcePort;
    }
}
