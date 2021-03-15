package run.star.plan.filter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author hecs
 * @date 2020-10-12 16:17
 */
public class JarTest {
    public static void getJarName(String jarFile) throws Exception {

        try {
            //通过将给定路径名字符串转换为抽象路径名来创建一个新File实例
            File f = new File(jarFile);
            URL url1 = f.toURI().toURL();
            URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url1}, Thread.currentThread().getContextClassLoader());

            //通过jarFile和JarEntry得到所有的类
            JarFile jar = new JarFile(jarFile);
            //返回zip文件条目的枚举
            Enumeration<JarEntry> enumFiles = jar.entries();
            JarEntry entry;

            //测试此枚举是否包含更多的元素
            while (enumFiles.hasMoreElements()) {
                entry = (JarEntry) enumFiles.nextElement();
                if (entry.getName().indexOf("META-INF") < 0) {
                    String classFullName = entry.getName();
                    if (!classFullName.endsWith(".class")) {
                        classFullName = classFullName.substring(0, classFullName.length() - 1);
                    } else {
                        //去掉后缀.class
                        String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                        //打印类名
                        if (className.contains("Facade")) {
                            System.out.println("\"/Users/hecs/kklgitworkspace/cpcoreprod/cpcoreprod-iface/src/main/java/" + className.replace(".", "/") + ".java\",");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        getJarName("/Users/hecs/kklgitworkspace/memberpowerprod/memberpowerprod-iface/target/memberpowerprod-iface-1.0.0.20210201-SNAPSHOT.jar");
        getJarName("/Users/hecs/kklgitworkspace/cpcoreprod/cpcoreprod-iface/target/cpcoreprod-iface-1.0.0.20210203-dyj-SNAPSHOT.jar");

    }

}
