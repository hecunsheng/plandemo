package star.plan.demo.aop.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @Author: hecs
 * @Date: 2018/11/8 15:34
 * @Description:
 */
@Service
public class AuthService {
    private final static Logger log = Logger.getLogger(AuthService.class);

    public Object login(String userName,String password){
        log.info("用户登录");
        return null;
    }

    public Object login(int userName,String password){
        log.info("用户登录");
        return null;
    }

    public Object login(String userName,int password){
        log.info("用户登录");
        return null;
    }

    public boolean logout(String loginName){
        log.info("注销登录");
        return true;
    }
}
