package com.yanqun.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanqun.demo.service.HttpAPIService;

@RestController
public class HttpClientController {
 
    @Resource	
    private HttpAPIService httpAPIService;
 
    @RequestMapping("httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }
}
