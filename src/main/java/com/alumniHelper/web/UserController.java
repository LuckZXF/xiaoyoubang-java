package com.alumniHelper.web;

import com.alumniHelper.bean.AlumniUser;
import com.alumniHelper.bean.Result;
import com.alumniHelper.dao.UserDao;
import com.alumniHelper.service.UserService;
import com.alumniHelper.util.ReqUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *  
 *  @since JDK 1.6 
 */
@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserDao userDao;


    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public Result register(HttpServletRequest request) {
        //帐号
        String username = request.getParameter("username");
        //昵称
        String nickname = request.getParameter("nickname");
        //密码
        String password = request.getParameter("password");
        //手机
        String mobile = request.getParameter("mobile");


        if (StringUtils.isBlank(username) || StringUtils.isBlank(nickname)
                || StringUtils.isBlank(password) || StringUtils.isBlank(mobile)) {
            return new Result(-1, "请求参数不正确");
        }

        AlumniUser user = new AlumniUser(username, nickname, password, mobile);
        user = userService.registerCloundMsg(user);
        if(StringUtils.isBlank(user.getToken())) {
            return new Result(-4, "注册云信失败");
        }
        if (userDao.saveUser(user)) {
            return new Result(0, "ok");
        }
        return new Result(-3, "该帐号已被注册");
    }

    public Result checkMobile(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");

        return new Result(-2, "验证码有误");
    }


    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(HttpServletRequest request) {
        //帐号
        String username = request.getParameter("username");
        //密码
        String password = request.getParameter("password");

        String body = ReqUtil.getRequestContent(request);
        System.out.println("登录请求的参数是: " + body);
        System.out.println("登录请求的username是: " + username);
        System.out.println("登录请求的password是: " + password);
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return new Result(-1, "请求参数不正确");
        }
        AlumniUser user = userDao.queryUser(username);
        if(user.getPassword().equals(password)) {
            return new Result(0, "登陆成功");
        }
        return new Result(-2, "登录失败");
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/modify")
    @ResponseBody
    public Result modify(HttpServletRequest request) {

        //帐号
        String username = request.getParameter("username");
        //昵称
        String nickname = request.getParameter("nickname");
        //密码
        String password = request.getParameter("password");
        //手机
        String mobile = request.getParameter("mobile");
        //性别
        String genderStr = request.getParameter("gender");
        //班级
        String classname = request.getParameter("classname");
        //学校
        String school = request.getParameter("school");
        //专业
        String major = request.getParameter("major");

        if(StringUtils.isBlank(username) || StringUtils.isBlank(nickname)
                || StringUtils.isBlank(password)) {
            return new Result(-1, "请求参数不正确");
        }
        AlumniUser user = userDao.queryUser(username);
        if(StringUtils.isNotBlank(password)) {
            user.setPassword(password);
        }
        if(StringUtils.isNotBlank(mobile)) {
            user.setMobile(mobile);
        }
        if(StringUtils.isNotBlank(genderStr)) {
            int gender = Integer.parseInt(genderStr);
            user.setGender(gender);
        }
        if(StringUtils.isNotBlank(school)) {
            user.setSchool(school);
        }
        if(StringUtils.isNotBlank(major)) {
            user.setMajor(major);
        }
        if(StringUtils.isNotBlank(classname)) {
            user.setClassname(classname);
        }
        if(userDao.updateUser(user)) {
            return new Result(0, "ok");
        }
        return new Result(-2, "修改失败");
    }

}
