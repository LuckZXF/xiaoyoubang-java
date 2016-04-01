package com.alumniHelper.bean;

/**
 *  
 *  ClassName: ${type_name} <br/> 
 *  Function: ${todo} ADD FUNCTION. <br/> 
 *  Reason: ${todo} ADD REASON(可选). <br/> 
 *  date: 16/3/9 下午8:41 <br/> 
 *  
 *  @author eno
 *  @version ${enclosing_type}${tags} 
 *  @since JDK 1.6 
 */
public class AlumniUser {
    private String username;
    private String nickname;
    private String password;
    private String mobile;
    private int gender; //性别 0:女 1:男
    private String registerTime; //注册时间
    private String ccid;
    private String token;
    private String school; //学校
    private String major; //专业
    private String graduationTime;//毕业时间
    private String classname; //班级名称
    private String avator; //头像图片地址
    private String payCode;//支付二维码图片地址

    public AlumniUser() {
    }

    public AlumniUser(String username, String nickname, String password, String mobile) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }
}
