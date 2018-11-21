package star.plan.hecs.demo.controller;

import com.google.common.collect.Maps;
import star.plan.hecs.demo.service.NamedService;
import star.plan.hecs.demo.service.TestService;
import star.plan.hecs.framework.annotation.IAutowired;
import star.plan.hecs.framework.annotation.IController;
import star.plan.hecs.framework.annotation.IRequestMapping;
import star.plan.hecs.framework.annotation.IRequestParam;
import star.plan.hecs.framework.servlet.IModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: hecs
 * @Date: 2018/11/21 11:26
 * @Description:
 */
@IController
@IRequestMapping("/test")
public class FirstAction {
    @IAutowired
    private TestService testService;
    @IAutowired("namedService")
    private NamedService namedService;

    @IRequestMapping("/login/.*.json")
    public IModelAndView testLogin(HttpServletRequest request, HttpServletResponse response,
     @IRequestParam("name") String name,@IRequestParam("addr") String addr){
        Map<String, Object> params = Maps.newHashMap();
        params.put("name", name);
        params.put("addr",addr);
        return new IModelAndView("first.hecs" , params);
    }
}
