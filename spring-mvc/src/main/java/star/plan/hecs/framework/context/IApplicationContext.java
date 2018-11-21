package star.plan.hecs.framework.context;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import star.plan.hecs.framework.annotation.IAutowired;
import star.plan.hecs.framework.annotation.IController;
import star.plan.hecs.framework.annotation.IService;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: hecs
 * @Date: 2018/11/20 17:45
 * @Description:
 */
public class IApplicationContext {
    //ioc容器
    Map<String,Object> instanceMapping = Maps.newConcurrentMap();

    //类似于内部的配置信息，我们在外面是看不到的
    //我们能够看到的只有ioc容器  getBean方法来间接调用的
    private List<String> classCache = Lists.newArrayList();

    private Properties config = new Properties();

    public IApplicationContext(String location) {
        InputStream is = null;

        try {
            //1、定位
            is = this.getClass().getClassLoader().getResourceAsStream(location);
            //2、载入
            config.load(is);
            //3、注册，把所有class找出来存着
            String packageName = config.getProperty("scanPackage");
            doRegister(packageName);
            //4、实例化需要ioc的对象 @Service @Controller  循环读取加载class
            doCreateBean();
            //5、注入
            populate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ioc容器已经初始化");
    }

    //把符合条件的class全部找出来，注册到缓存中去
    private void doRegister(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.","/"));
        File dir = new File(url.getFile());
        for(File file:dir.listFiles()){
            //如果是一个文件夹，继续递归
            if(file.isDirectory()){
                doRegister(packageName+"."+file.getName());
            }else {
                classCache.add(packageName+"."+file.getName().replace(".class","").trim());
            }
        }
    }

    private void doCreateBean() {
        //检查看有没有注册信息,注册信息里面保存了所有的class名字
        //BeanDefinition 保存了类的名字，也保存类和类之间的关系(Map/list/Set/ref/parent)
        //processArray
        if(CollectionUtils.isEmpty(classCache)){
            return;
        }
        try {
            for(String className:classCache){
                Class<?> classz = Class.forName(className);
                //那个类需要初始化，哪个类不要初始化
                //只要加了  @Service  @Controller都要初始化
                if(classz.isAnnotationPresent(IController.class)){
                    //名字起啥？ 默认就是类名首字母小写
                    String id = this.lowerFirstChar(classz.getSimpleName());
                    instanceMapping.put(id,classz.newInstance());
                }else if(classz.isAnnotationPresent(IService.class)){
                    IService service = classz.getAnnotation(IService.class);
                    //如果设置了自定义名称，优先使用他自己定义的名字
                    String id = service.value();
                    if(!StringUtils.isEmpty(id.trim())){
                        instanceMapping.put(id,classz.newInstance());
                        continue;
                    }
                    //如果是空的，就用默认规则
                    //1、类名首字母小写
                    //如果这个类是接口
                    //2、可以根据类型类匹配
                    Class<?>[] interfaces = classz.getInterfaces();
                    //如果这个类实现了接口，就用接口的类型作为id
                    for(Class<?> i : interfaces){
                        instanceMapping.put(i.getName(),classz.newInstance());
                    }
                }else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //依赖注入
    private void populate() {
        //首先要判断ioc容器中有没有东西
        if(instanceMapping.isEmpty()){ return ;}

        for(Map.Entry<String,Object> entry : instanceMapping.entrySet()){
            //把所有的属性全部取出来，包括私有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for(Field field:fields){
                if(!field.isAnnotationPresent(IAutowired.class)){
                    continue;
                }

                IAutowired autowired = field.getAnnotation(IAutowired.class);

                String id = autowired.value().trim();
                //如果id为空，也就是说，自己没有设置，默认根据类型来注入
                if(StringUtils.isEmpty(id)){
                        id = field.getType().getName();
                }
                //把私有变量开放访问权限
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),instanceMapping.get(id));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }
    /**
     * 将首字母小写
     * @param str
     * @return
     */
    private String lowerFirstChar(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Map<String, Object> getInstanceMapping() {
        return instanceMapping;
    }

    public Properties getConfig() {
        return config;
    }
}
