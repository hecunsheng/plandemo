package run.star.plan.serializable;

/**
 * @author hecs
 * @date 2019-06-21 19:39
 */
public class ProtobufTest {

    public static void main(String[] args) {
        UserProtos.User user = UserProtos.User.newBuilder().setAge(18).setName("hecs").build();
        byte[] bytes = user.toByteArray();
        System.out.println("length:" + bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }
        System.out.println();
        System.out.println(user);
    }
}
