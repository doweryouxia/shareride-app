package com.dower.sharerideapp.controller.weichat;

import com.alibaba.fastjson.JSONObject;
import com.dower.sharerideapp.utils.HttpRequestUtil;
import com.dower.sharerideapp.utils.weichat.SignUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @Author: wangwei
 * @Description:
 * @Date: Created in 17:02   2018/7/10
 */
@Controller
@RequestMapping("/weChatSend")
public class WeichatMainController {
    private static final Logger LOGGER = LogManager.getLogger(WeichatMainController.class);
    /*****
     * 确认请求来自微信服务器
     *
     *  signature  微信加密签名
     *  timestamp   时间戳
     *  nonce  随机数
     *  echostr  随机字符串
     * @return
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public String doGetMethod(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (signature != null && timestamp != null && nonce != null) {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                return echostr;
            }
        }
        return null;
    }

    @RequestMapping("/rediractHomeUrl")
    public ModelAndView rediractHomeUrl(String code, String state, HttpServletRequest request, HttpServletResponse response)   {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        response.setCharacterEncoding("utf-8");
        try {
            // 调用核心业务类接收消息、处理消息
            LOGGER.info("code:"+code+";state:"+state);
            String openid = getOpenId(code);
            LOGGER.info("openid=========="+openid);

            ModelAndView mv = new ModelAndView();
            //封装要显示到视图的数据
            mv.addObject("openid",openid);
            //视图名
            mv.setViewName("fileList");
            response.sendRedirect("http://demo.doweryouxia.com/demo.app.vc/fileList.html?openid="+openid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取openid
     * @param code
     * @return
     */
    public String getOpenId(String code){
        Map<String, String> params = new HashMap();
        params.put("secret", "e60292f15095ba4ed1992aa8f3647989");
        params.put("appid", "wxee4726bdcff75c74");
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        String result = HttpRequestUtil.request("https://api.weixin.qq.com/sns/oauth2/access_token", params,false);
        JSONObject jsonObject = JSONObject.parseObject(result);

        String openid = jsonObject.get("openid").toString();

        return openid;
    }
}
