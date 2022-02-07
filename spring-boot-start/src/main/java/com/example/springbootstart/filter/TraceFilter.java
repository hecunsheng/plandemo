package com.example.springbootstart.filter;

import com.alibaba.arms.tracing.Span;
import com.alibaba.arms.tracing.Tracer;
import com.example.springbootstart.util.TraceIdUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述
 * @author peanutcxb
 * @date 2021/10/13
 */
@Component
public class TraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        try {
            String traceId = request.getHeader("traceId");
            if (StringUtils.isEmpty(traceId)) {
                Span span = Tracer.builder().getSpan();
                traceId = span.getTraceId();
            }
            //设置traceId
            TraceIdUtil.setTraceId(traceId);
            response.setHeader("traceId", TraceIdUtil.getTraceId());
            //执行
            filterChain.doFilter(request, response);
        } finally {
            TraceIdUtil.clear();
        }
    }
}