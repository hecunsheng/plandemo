package star.plan.hecs.framework.servlet;

import lombok.Data;

import java.util.Map;

/**
 * @Author: hecs
 * @Date: 2018/11/21 15:51
 * @Description:
 */
@Data
public class IModelAndView {
    //页面模板
    private String view;
    //要往页面带过去的值
    private Map<String,Object> model;

//    public IModelAndView(String view) {
//        this.view = view;
//    }
//
    public IModelAndView(String view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }
}
