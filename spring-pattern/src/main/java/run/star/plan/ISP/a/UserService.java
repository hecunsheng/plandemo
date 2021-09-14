package run.star.plan.ISP.a;

/**
 * @author hecs
 * @date 2021/7/19 20:55
 */
public interface UserService {
    boolean register(String cellphone, String password);
    boolean login(String cellphone, String password);
//        UserInfo getUserInfoById(long id);
//        UserInfo getUserInfoByCellphone(String cellphone);
}