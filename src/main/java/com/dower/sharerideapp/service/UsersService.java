package com.dower.sharerideapp.service;


import com.dower.sharerideapp.domain.repository.UsersDao;

import com.dower.sharerideapp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @Author: wangwei
 * @Description:
 * @Date: Created in 14:04   2018/6/28
 */
@Service
public class UsersService {
    @Autowired
    private UsersDao insurDao;
    public Result getInsureInfo(){
        Result result = new Result();
        try {
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("insurList",insurDao.queryUserList());
            result.setResultInfo(resultMap);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
