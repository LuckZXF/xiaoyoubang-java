package com.alumniHelper.service;

import com.alumniHelper.bean.AlumniUser;
import com.alumniHelper.util.CheckSumBuilder;
import com.alumniHelper.util.ConstantConfig;
import com.alumniHelper.util.GetNonce;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public String getMobileCode(String mobile) {
        return null;
    }


    public AlumniUser registerCloundMsg(AlumniUser user) {
        String accid = user.getUsername();
        String nonce = GetNonce.getKey();
        String appKey = ConstantConfig.getString("APPKEY");
        String curTime = new Date().getTime()+"";
        String checkSum = CheckSumBuilder.getCheckSum(nonce, curTime);
        StringBuffer url = new StringBuffer("https://api.netease.im/nimserver/user/create.action");
        url.append("?accid=" + accid);
        if(StringUtils.isNotBlank(user.getNickname())) {
            url.append("&name=" + user.getNickname());
        }
        if(StringUtils.isNotBlank(user.getAvator())) {
            url.append("&icon=" + user.getAvator());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("AppKey", appKey);
        headers.add("Nonce", nonce);
        headers.add("CurTime", curTime);
        headers.add("CheckSum", checkSum);
        headers.add("Content-Type", "application/json;charset=utf-8");



        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        String resultStr = restTemplate.postForObject(url.toString(), requestEntity, String.class);
        System.out.println("resultStr:" + resultStr);
        JSONObject result = JSONObject.fromObject(resultStr);
        int code = result.getInt("code");
        if(code == 200) {
            JSONObject info = result.getJSONObject("info");
            String token = info.getString("token");
            user.setToken(token);
        }
        return user;
    }


}
