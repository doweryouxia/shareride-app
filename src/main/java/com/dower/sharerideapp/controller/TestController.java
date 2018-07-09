package com.dower.sharerideapp.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @param
 * @Author: wangwei
 * @Description:
 * @Date: Created in 10:06   2018/4/8
 */

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @RequestMapping("/hello1")
    public String index1() {
        return "Hello World";
    }
}