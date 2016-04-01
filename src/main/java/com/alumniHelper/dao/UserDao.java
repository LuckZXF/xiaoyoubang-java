package com.alumniHelper.dao;

import com.alumniHelper.bean.AlumniUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 *  
 *  ClassName: ${type_name} <br/> 
 *  Function: ${todo} ADD FUNCTION. <br/> 
 *  Reason: ${todo} ADD REASON(可选). <br/> 
 *  date: 16/3/9 下午8:01 <br/> 
 *  
 *  @author eno
 *  @version ${enclosing_type}${tags} 
 *  @since JDK 1.6 
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 插入一条用户记录
     * @param user
     * @return
     */
    public boolean saveUser(AlumniUser user){

        String sql = "insert into AlumniHelperUser (username,password,"
                + "mobile,nickname, gender, ccid, token, school, major, " +
                "classname, avator, payCode, graduationTime) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] data = {user.getUsername(), user.getPassword(), user.getMobile(),user.getNickname(),
                user.getGender(), user.getCcid(),user.getToken(),user.getSchool(), user.getMajor(),
                user.getClassname(),user.getAvator(),user.getPayCode(), user.getGraduationTime()};
        try {
            int row = jdbcTemplate.update(sql, data);
            if(row >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据帐号查询一个用户
     * @param username
     * @return
     */
    public AlumniUser queryUser(String username){
        String sql = "select * from AlumniHelperUser where username = ?";
        Object[] data = {username};
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, data);
        if(rs.next()) {
            String nickname=rs.getString("nickname");
            String password=rs.getString("password");
            String mobile=rs.getString("mobile");
            int gender=rs.getInt("gender");
            String registerTime=rs.getString("registerTime");
            String ccid=rs.getString("ccid");
            String token=rs.getString("token");
            String school=rs.getString("school");
            String major=rs.getString("major");
            String classname=rs.getString("classname");
            String avator=rs.getString("avator");
            String payCode=rs.getString("payCode");
            String graduationTime = rs.getString("graduationTime");
            AlumniUser user = new AlumniUser();
            user.setNickname(nickname);
            user.setPassword(password);
            user.setMobile(mobile);
            user.setGender(gender);
            user.setRegisterTime(registerTime);
            user.setCcid(ccid);
            user.setToken(token);
            user.setSchool(school);
            user.setMajor(major);
            user.setClassname(classname);
            user.setAvator(avator);
            user.setPayCode(payCode);
            user.setGraduationTime(graduationTime);
            return user;
        }
        return null;
    }

    /**
     * 根据更新用户信息
     * @param user
     * @return
     */
    public boolean updateUser(AlumniUser user){
        String sql = "update AlumniHelperUser set password=?,"
                + "mobile=?,nickname=?, gender=?, ccid=?, token=?, " +
                "school=?, major=?,classname=?, avator=?, payCode=?, graduationTime=? where username = ?";
        Object[] data = {user.getPassword(), user.getMobile(),user.getNickname(), user.getGender(),
                user.getCcid(),user.getToken(),user.getSchool(), user.getMajor(), user.getClassname(),
                user.getAvator(),user.getPayCode(),user.getGraduationTime(), user.getUsername()};
        try {
            int row = jdbcTemplate.update(sql, data);
            if(row >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
