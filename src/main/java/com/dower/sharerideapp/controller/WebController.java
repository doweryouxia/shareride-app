package com.dower.sharerideapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @param
 * @Author: wangwei
 * @Description:
 * @Date: Created in 10:39   2018/6/12
 */
@Controller

public class WebController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/orderList")
    public String index1() {
        return "order/orderList";
    }
}
