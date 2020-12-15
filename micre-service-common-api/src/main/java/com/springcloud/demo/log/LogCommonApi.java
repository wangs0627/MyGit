package com.springcloud.demo.log;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "micro-service-log-provider")
public interface LogCommonApi
{
    @RequestMapping(method = RequestMethod.GET, value = "/log/operLog")
    public String operLogReceive();
}

