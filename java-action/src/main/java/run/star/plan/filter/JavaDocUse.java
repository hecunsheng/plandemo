package run.star.plan.filter;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.Main;

/**
 * @author hecs
 * @date 2020-10-12 16:04
 */
public class JavaDocUse {
    private static RootDoc rootDoc;

    public static class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocUse.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static void show() {
        ClassDoc[] classes = rootDoc.classes();
        for (ClassDoc classDoc : classes) {
            MethodDoc[] methodDocs = classDoc.methods();
            for (MethodDoc methodDoc : methodDocs) {
                // 打印出方法上的注释
                System.out.println("cpcoreprod." + classDoc.name() + "." + methodDoc.name());
//                System.out.println(methodDoc.commentText().replace("\n", " "));
//                String commentText = methodDoc.commentText();
//                int beginIndex = commentText.indexOf("\n");
//                if(beginIndex != -1){
//                    System.out.println(commentText.substring(0,beginIndex));
//                }else {
//                    System.out.println(methodDoc.commentText().replace("\n", " "));
//                }
            }
        }
    }

    public static void show1() {
        ClassDoc[] classes = rootDoc.classes();
        for (ClassDoc classDoc : classes) {
            MethodDoc[] methodDocs = classDoc.methods();
            for (MethodDoc methodDoc : methodDocs) {
                String commentText = methodDoc.commentText();
                int beginIndex = commentText.indexOf("\n");
                if (beginIndex != -1) {
                    System.out.println(commentText.substring(0, beginIndex));
                } else {
                    System.out.println(methodDoc.commentText().replace("\n", " "));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

//        String a = "cn.estudy.course.service.facade.CourseServiceProviderFacade";
//        Class clazz = Class.forName(a);
//        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
//        System.out.println(url.toURI());

        Main.execute(new String[]{"-doclet",
                Doclet.class.getName(),
                "-encoding", "utf-8", "-classpath", "",
                "/Users/hecs/kklgitworkspace/cpcoreprod/cpcoreprod-iface/src/main/java/la/kaike/cpcoreprod/service/lesson/facade/LectureFacade.java"
        });
        show();
        show1();
    }
}
