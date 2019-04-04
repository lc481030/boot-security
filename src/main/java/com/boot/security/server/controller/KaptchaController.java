package com.boot.security.server.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boot.security.server.utils.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
public class KaptchaController {

    /**
     * 1、验证码工具
     */
    @Resource
    DefaultKaptcha defaultKaptcha;

    /**
     * 2、生成验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha.html")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 3、校对验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/imgvrifyControllerDefaultKaptcha.html")
    @ResponseBody
    public Map<String,String> imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Map<String,String> map = new HashMap<>();

        String rightCode = (String) httpServletRequest.getSession().getAttribute("rightCode");
        String tryCode = httpServletRequest.getParameter("tryCode");
        String phone = httpServletRequest.getParameter("phone");
        System.out.println("rightCode:"+rightCode+" ———— tryCode:"+tryCode);
        if (!rightCode.equals(tryCode)) {
            map.put("info", "错误的验证码");
        } else {
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //填写发送短信的号码
                params.add(new BasicNameValuePair("mobile", phone));
                //测试默认签名。在平台注册之后可以添加自定义签名，可发送自定义内容
                String str="0123456789";
                StringBuilder sb=new StringBuilder(4);
                for(int i=0;i<4;i++)
                {
                    char ch=str.charAt(new Random().nextInt(str.length()));
                    sb.append(ch);
                }
                params.add(new BasicNameValuePair("message", "验证码："+sb.toString()+"【会泽千寻】"));
                HttpEntity reqEntity = new UrlEncodedFormEntity(params, "UTF-8");
                int resultCode = HttpClient.sendPost(reqEntity);
                httpServletRequest.getSession().setAttribute(phone,sb.toString());
                if (resultCode !=0){
                    map.put("info", "验证码发送失败");
                }else{
                    httpServletRequest.getSession().setAttribute(phone,sb.toString());
                    map.put("info", "验证码发送成功");
                }
            }catch (Exception e){

            }
        }
        return map;
    }


    /**
     * 3、校对手机验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/phoneCodeVerification.html")
    @ResponseBody
    public Map<String,String> phoneCodeVerification(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        Map<String,String> map = new HashMap<>();
        String phone = httpServletRequest.getParameter("phone");
        String phoneCode = httpServletRequest.getParameter("phoneCode");
        String getCode = (String) httpServletRequest.getSession().getAttribute(phone);
        if (phoneCode.equals(getCode)){
            map.put("status","1");
            map.put("info", "验证码正确");
        }else{
            map.put("status","0");
            map.put("info", "验证码错误");
        }
        return map;
    }

}
