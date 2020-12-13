package edu.fudan.onlinehotelbooking.controller;

import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.util.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by 姜向阳
 * on 2020/5/18
 */
@Controller
public class VerifyController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VerifyUtil.class);
    public static final String RANDOMCODEKEY= "RandomCheckCode";//放到session中的key，以用于与从前端接收到的输入比较
    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyUtil randomValidateCode = new VerifyUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }
    @GetMapping(value="/checkCode")
    @ResponseBody
    public Result checkCode(HttpServletRequest request , @RequestParam String checkCode){
        //TODO: 验证码比较
        String sessionCheckCode = (String) request.getSession().getAttribute(RANDOMCODEKEY);
        if (checkCode.toLowerCase().equals(sessionCheckCode.toLowerCase())){   //验证码判断，不区分大小写
            return ResultGenerator.genSuccessResult();
        }
        else{
            return ResultGenerator.genFailResult("验证码错误");
        }

    }

}

