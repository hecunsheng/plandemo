package com.example.springbootstart.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

/**
 * 功能描述
 * @author peanutcxb
 * @date 2021/10/13
 */
public class TraceIdUtil {
    private static final String TRACE_ID = "traceId";
    /**
     * 当traceId为空时，显示的traceId。随意。
     */
    private static final String DEFAULT_TRACE_ID = "0";

    /**
     * 设置traceId
     */
    public static void setTraceId(String traceId) {
        //如果参数为空，则设置默认traceId
        traceId = StrUtil.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
        //将traceId放到MDC中
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 获取traceId
     */
    public static String getTraceId() {
        //获取
        String traceId = MDC.get(TRACE_ID);
        //如果traceId为空，则返回默认值
        return StrUtil.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
    }

    public static void clear() {
        MDC.clear();
    }
}