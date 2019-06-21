package run.star.plan.serializable;

/**
 * @author hecs
 * @date 2019-06-21 14:11
 */
public class SerializeTest {

    public static void main(String[] args) {
//        ISerializer iSerializer = new JavaSerialize();
//        ISerializer iSerializer = new JavaSerializeWithFile();
//        ISerializer iSerializer = new XStreamSerialize();
        ISerializer iSerializer = new HessianSerialize();
//        ISerializer iSerializer = new FastJsonSerialize();

        User user = User.builder().age(18).name("hecs").build();

        /**
         *
         * java原生: byte.length:165
         * xmlstream: byte.length:239
         * hessian: byte.length:47
         * fastjson: byte.length:10
         * */

        byte[] bytes = iSerializer.serialize(user);
        System.out.println("byte.length:" + bytes.length + " ->" + new String(bytes));

        User reSerializeUser = iSerializer.reSerialize(bytes, User.class);
        System.out.println(reSerializeUser);
    }
}
