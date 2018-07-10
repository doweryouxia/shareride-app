package com.dower.sharerideapp.controller.weichat;

import com.dower.sharerideapp.utils.weichat.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @param
 * @Author: wangwei
 * @Description:
 * @Date: Created in 17:02   2018/7/10
 */
@Controller
@RequestMapping("/weChatSend")
public class WeichatMainController {
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

}
