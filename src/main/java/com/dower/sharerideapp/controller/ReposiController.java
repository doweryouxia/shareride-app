package com.dower.sharerideapp.controller;

import com.dower.sharerideapp.service.UsersService;
import com.dower.sharerideapp.utils.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @param
 * @Author: wangwei
 * @Description:
 * @Date: Created in 14:13   2018/6/28
 */
@RestController
public class ReposiController {
    private static final Logger LOGGER = LogManager.getLogger(ReposiController.class);
    @Autowired
    UsersService usersService;
    @RequestMapping("/getUserList")
    public Result getInsurInfo(HttpServletRequest request, HttpServletResponse response) {
        return usersService.getInsureInfo();
    }

    @RequestMapping("/getUser")
    public Result getUser(HttpServletRequest request, HttpServletResponse response) {
        return usersService.queryUser();
    }

}
