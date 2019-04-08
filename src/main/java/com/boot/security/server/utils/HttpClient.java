package com.boot.security.server.utils;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClient {


    public static String sendPost(HttpEntity reqEntity) {
        //luosimao短信平台短信发送接口URL
        HttpPost post = new HttpPost("http://sms-api.luosimao.com/v1/send.json");
        String resultCode = "-1";
        //“d609b769db914a4d959bae3414ed1f7X” --APIkey，在luosimao.com注册登陆以后可以得到
        post.setHeader("Authorization", "Basic " + Base64.encodeBase64String("api:key-91530545f0aaf11f65afee38f8f087aa".getBytes()));
        post.setEntity(reqEntity);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(post);
            HttpEntity respEntity = response.getEntity();
            //status code,如200
            int statusCode = response.getStatusLine().getStatusCode();
            //result,如{"error":0,"msg":"ok"}
            String respString = EntityUtils.toString(respEntity);
            System.out.println(respString);
            Map maps = (Map) JSON.parse(respString);
            for (Object map : maps.entrySet()){
                System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
            }
            resultCode = maps.get("error").toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return resultCode;

    }
}
