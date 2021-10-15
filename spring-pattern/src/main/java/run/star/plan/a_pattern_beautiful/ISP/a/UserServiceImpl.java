package run.star.plan.a_pattern_beautiful.ISP.a;

/**
 * @author hecs
 * @date 2021/7/19 20:57
 */
public class UserServiceImpl implements UserService,RestrictedUserService{
    @Override
    public boolean deleteUserByCellphone(String cellphone) {
        return false;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }

    @Override
    public boolean register(String cellphone, String password) {
        return false;
    }

    @Override
    public boolean login(String cellphone, String password) {
        return false;
    }
}
