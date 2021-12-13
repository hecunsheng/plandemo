package run.star.plan.javabase.base;

import java.util.Optional;

/**
 * @Author hecs
 * @Date 2021/10/25 09:52
 */
public class Optional01 {
    public static void main(String[] args) {
        Optional<OptionalUser> optionalUser = Optional.empty();
//        optionalUser.get();
        System.out.println(optionalUser.isPresent());
//        Optional<OptionalUser> nullOpt = Optional.of(null);
//        nullOpt.get();
        Optional<OptionalUser> ofNullableOpt = Optional.ofNullable(new OptionalUser(1, "xiaoming"));
        if (ofNullableOpt.isPresent()) {
            System.out.println(ofNullableOpt.get());
        }
        OptionalUser nullUser = null;
        OptionalUser orElseUser = Optional.ofNullable(nullUser).orElse(new OptionalUser(2, "xiaoming2"));
        System.out.println(orElseUser.getName());
//        Optional.ofNullable(nullUser).orElseThrow(() ->
//                new RuntimeException("xx"));

        // 1、map转换方法
        OptionalUser user = new OptionalUser(99, "Java");
        // user = null ;
        String name = Optional.ofNullable(user)
                .map(u -> u.getName()).orElse("c++");
        System.out.println(name);
        // 2、过滤方法
        Optional<OptionalUser> optUser01 = Optional.ofNullable(user)
                .filter(u -> u.getName() != null && u.getName().contains("c++"));
        // NoSuchElementException
        System.out.println(optUser01.get().getName());
    }

}
