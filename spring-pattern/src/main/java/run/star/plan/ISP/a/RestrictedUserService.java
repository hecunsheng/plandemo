package run.star.plan.ISP.a;

/**
 * @author hecs
 * @date 2021/7/19 20:56
 */
public interface RestrictedUserService {
    boolean deleteUserByCellphone(String cellphone);
    boolean deleteUserById(long id);
}
