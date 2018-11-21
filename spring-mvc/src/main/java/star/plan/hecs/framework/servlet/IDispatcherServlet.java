package star.plan.hecs.framework.servlet;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;
import star.plan.hecs.framework.annotation.IController;
import star.plan.hecs.framework.annotation.IRequestMapping;
import star.plan.hecs.framework.annotation.IRequestParam;
import star.plan.hecs.framework.context.IApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: hecs
 * @Date: 2018/11/20 17:32
 * @Description:
 */
public class IDispatcherServlet extends HttpServlet {
    private static final String LOCATION = "contextConfigLocation";
    //url映射关系
    private List<Handler> handlerMapping = Lists.newArrayList();
    //视图解析器
    private List<ViewResolver> viewResolvers = Lists.newArrayList();
    //执行程序适配器
    private Map<Handler, HandlerAdapter> adapterMapping = Maps.newHashMap();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //IOC容器必须要先初始化
        //假装容器已启动
        IApplicationContext context = new IApplicationContext(config.getInitParameter(LOCATION));

        //请求解析
        initMultipartResolver(context);
        //多语言、国际化
        initLocaleResolver(context);
        //主题view层的
        initThemeResolver(context);

        //============== 重要 ================
        //解析url和Method的关联关系
        initHandlerMappings(context);
        //适配器（匹配的过程）
        initHandlerAdapters(context);

        //异常解析
        initHandlerExceptionResolvers(context);
        //视图转发（根据视图名字匹配到一个具体模板）
        initRequestToViewNameTranslator(context);
        //解析模板中的内容（拿到服务器传过来的数据，生成HTML代码）
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    //在这里调用自己写的Controller的方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception, Msg :" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            //先取出来一个Handler，从HandlerMapping取
            Handler handler = getHandler(req);
            if (handler == null) {
                resp.getWriter().write("404 Not Found");
                return;
            }
            //再取出来一个适配器
            //再由适配去调用我们具体的方法
            HandlerAdapter ha = getHandlerAdapter(handler);
            IModelAndView mv = ha.handler(req, resp, handler);


            //写一个咕泡模板框架
            //Veloctiy #
            //Freemark  #
            //JSP   ${name}

            //咕泡模板   @{name}
            applyDefaultViewName(resp, mv);

        } catch (Exception e) {
            throw e;
        }
    }

    private void applyDefaultViewName(HttpServletResponse resp, IModelAndView mv) throws Exception {
        if (null == mv) {
            return;
        }
        if (viewResolvers.isEmpty()) {
            return;
        }

        for (ViewResolver resolver : viewResolvers) {
            if (!mv.getView().equals(resolver.getViewName())) {
                continue;
            }
            String r = resolver.parse(mv);
            if (r != null) {
                resp.getWriter().write(r);
                break;
            }
        }
    }

    private HandlerAdapter getHandlerAdapter(Handler handler) {
        if (adapterMapping.isEmpty()) {
            return null;
        }
        return adapterMapping.get(handler);
    }

    private Handler getHandler(HttpServletRequest req) {
        //循环handlerMapping
        if (handlerMapping.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (Handler handler : handlerMapping) {
            //根据样式匹配是否包含url的方法
            Matcher matcher = handler.pattern.matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }
        return null;
    }

    protected void initMultipartResolver(IApplicationContext context) {
    }

    protected void initLocaleResolver(IApplicationContext context) {
    }

    protected void initThemeResolver(IApplicationContext context) {
    }

    //解析url和Method的关联关系
    protected void initHandlerMappings(IApplicationContext context) {
        Map<String, Object> ioc = context.getInstanceMapping();
        if (ioc.isEmpty()) {
            return;
        }
        //只要是由Cotroller修饰类，里面方法全部找出来
        //而且这个方法上应该要加了RequestMaping注解，如果没加这个注解，这个方法是不能被外界来访问的
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> classz = entry.getValue().getClass();
            if (!classz.isAnnotationPresent(IController.class)) {
                continue;
            }
            String url = "";
            if (classz.isAnnotationPresent(IRequestMapping.class)) {
                IRequestMapping requestMapping = classz.getAnnotation(IRequestMapping.class);
                url = requestMapping.value();
            }

            //扫描Controller下面的所有方法
            Method[] methods = classz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(IRequestMapping.class)) {
                    continue;
                }
                IRequestMapping requestMapping = method.getAnnotation(IRequestMapping.class);
                String regex = (url + requestMapping.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);

                handlerMapping.add(new Handler(entry.getValue(), method, pattern));

                System.out.println("Mapping url:" + regex + "  method:" + method.toString());
            }
        }
    }

    //适配器（匹配的过程）
    //主要是用来动态匹配我们参数的
    protected void initHandlerAdapters(IApplicationContext context) {
        if (handlerMapping.isEmpty()) {
            return;
        }
        //参数类型作为key，参数的索引作为值
        Map<String, Integer> paramMapping = Maps.newHashMap();

        for (Handler handler : handlerMapping) {
            //把这个方法上面所有的参数全部获取到
            Class<?>[] paramsTypes = handler.method.getParameterTypes();
            //有顺序，但是通过反射，没法拿到我们参数名字
            //匹配自定参数列表
            for (int i = 0; i < paramsTypes.length; i++) {

                Class<?> type = paramsTypes[i];

                if (type == HttpServletRequest.class ||
                        type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }
            //这里是匹配Request和Response
            Annotation[][] pa = handler.method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                //匹配基于注解的参数
                for (Annotation annotation : pa[i]) {
                    //判断是否为此注解
                    if (annotation instanceof IRequestParam) {
                        String paramName = ((IRequestParam) annotation).value();
                        if (!StringUtils.isEmpty(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }
            //一个处理程序，对应一个适配器
            adapterMapping.put(handler, new HandlerAdapter(paramMapping));
        }
    }

    protected void initHandlerExceptionResolvers(IApplicationContext context) {
    }

    protected void initRequestToViewNameTranslator(IApplicationContext context) {
    }

    //解析模板中的内容（拿到服务器传过来的数据，生成HTML代码）
    protected void initViewResolvers(IApplicationContext context) {
        //模板一般是不会放到WebRoot下的，而是放在WEB-INF下，或者classes下
        //这样就避免了用户直接请求到模板
        //加载模板的个数，存储到缓存中
        //检查模板中的语法错误

        String tempateRoot = context.getConfig().getProperty("templateRoot");

        //归根到底就是一个文件，普通文件
        String rootPath = this.getClass().getClassLoader().getResource(tempateRoot).getFile();

        File rootDir = new File(rootPath);
        for (File template : rootDir.listFiles()) {
            viewResolvers.add(new ViewResolver(template.getName(), template));
        }
    }

    protected void initFlashMapManager(IApplicationContext context) {
    }


    /**
     * HandlerMapping 定义
     *
     * @author hecs
     */
    protected class Handler {
        protected Object contorller;
        protected Method method;
        protected Pattern pattern;

        protected Handler(Object contorller, Method method, Pattern pattern) {
            this.contorller = contorller;
            this.method = method;
            this.pattern = pattern;
        }
    }

    protected class ViewResolver {
        private String viewName;
        private File file;

        protected ViewResolver(String viewName, File file) {
            this.viewName = viewName;
            this.file = file;
        }

        protected String parse(IModelAndView mv) throws Exception {
            StringBuffer sb = new StringBuffer();
            RandomAccessFile ra = new RandomAccessFile(this.file, "r");
            try {
                //模板框架的语法是非常复杂，但是，原理是一样的
                //无非都是用正则表达式来处理字符串而已
                //就这么简单，不要认为这个模板框架的语法是有多么的高大上
                //来我现在来做一个最接地气的模板，也就是咕泡学院独创的模板语法
                String line = null;
                while (null != (line = ra.readLine())) {
                    Matcher m = matcher(line);
                    while (m.find()) {
                        for (int i = 1; i <= m.groupCount(); i++) {
                            String paramName = m.group(i);
                            Object paramValue = mv.getModel().get(paramName);
                            if (null == paramValue) {
                                continue;
                            }
                            line = line.replaceAll("@\\{" + paramName + "\\}", paramValue.toString());
                        }
                    }
                    sb.append(line);
                }
            } finally {
                ra.close();
            }
            return sb.toString();
        }

        protected Matcher matcher(String str) {
            Pattern pattern = Pattern.compile("@\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(str);
            return m;
        }

        public String getViewName() {
            return viewName;
        }
    }

    /**
     * 方法适配器
     *
     * @author hecs
     */
    private class HandlerAdapter {

        private Map<String, Integer> paramsMapping;

        public HandlerAdapter(Map<String, Integer> paramsMapping) {
            this.paramsMapping = paramsMapping;
        }

        //主要目的是用反射调用url对应的method
        protected IModelAndView handler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception {
            //为什么要传req、为什么要穿resp、为什么传handler
            Class<?>[] paramTypes = handler.method.getParameterTypes();
            //要想给参数赋值，只能通过索引号来找到具体的某个参数
            Object[] paramValues = new Object[paramTypes.length];

            Map<String, String[]> params = request.getParameterMap();

            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                if (!this.paramsMapping.containsKey(param.getKey())) {
                    continue;
                }
                //取出参数对应索引值
                int index = this.paramsMapping.get(param.getKey());
                //单个赋值是不行的
                paramValues[index] = castStringValue(value, paramTypes[index]);
            }
            //request 和 response 要赋值
            String respName = HttpServletResponse.class.getName();
            if (this.paramsMapping.containsKey(respName)) {
                int respIndex = this.paramsMapping.get(respName);
                paramValues[respIndex] = response;
            }

            boolean isModelAndView = handler.method.getReturnType() == IModelAndView.class;

            Object r = handler.method.invoke(handler.contorller, paramValues);
            if (isModelAndView) {
                return (IModelAndView) r;
            } else {
                return null;
            }
        }
        private Object castStringValue(String value, Class<?> clazz) {
            if (clazz == String.class) {
                return value;
            } else if (clazz == Integer.class) {
                return Integer.valueOf(value);
            } else if (clazz == int.class) {
                return Integer.valueOf(value).intValue();
            } else {
                return null;
            }
        }
    }
}