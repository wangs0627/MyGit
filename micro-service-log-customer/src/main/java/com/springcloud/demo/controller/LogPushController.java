package com.springcloud.demo.controller;

import com.springcloud.demo.log.LogCommonApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogPushController
{
    @Autowired
    private LogCommonApi logCommonApi;

    @GetMapping("/log/operLog/push")
    private String operLog()
    {
        return logCommonApi.operLogReceive();
    }
}