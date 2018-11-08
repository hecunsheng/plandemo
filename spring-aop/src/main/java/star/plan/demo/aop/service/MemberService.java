package star.plan.demo.aop.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author: hecs
 * @Date: 2018/11/8 15:34
 * @Description:
 */
@Service
public class MemberService {

    private final static Logger log = Logger.getLogger(MemberService.class);

    public boolean remove(long id) throws Exception{
        log.info("删除用户");
        throw new Exception("这是我们自己跑出来的异常");
    }


    public String modify(long id){
        log.info("修改用户");
        return new HashMap<String,String>().put("007","星爷");
    }

    public boolean query(long id){
        log.info("查询用户");
        return true;
    }
}
