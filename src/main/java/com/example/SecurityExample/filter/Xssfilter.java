//package com.example.SecurityExample.filter;
//
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Filters to prevent XSS attacks
// */
//@Component
//public class XssFilter implements Filter {
//    /**
//     * Exclude links
//     */
//    private List<String> excludes = new ArrayList<>();
//
//    /**
//     * xss Filter switch
//     */
//    private boolean enabled = false;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        String tempExcludes = filterConfig.getInitParameter("excludes");
//        String tempEnabled = filterConfig.getInitParameter("enabled");
//        if (StrUtil.isNotEmpty(tempExcludes)) {
//            String[] url = tempExcludes.split(",");
//            Collections.addAll(excludes, url);
//        }
//        if (StrUtil.isNotEmpty(tempEnabled)) {
//            enabled = Boolean.valueOf(tempEnabled);
//        }
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        if (handleExcludeUrl(req)) {
//            chain.doFilter(request, response);
//            return;
//        }
//        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
//        chain.doFilter(xssRequest, response);
//    }
//
//    /**
//     * Judge whether the current path needs to be filtered
//     */
//    private boolean handleExcludeUrl(HttpServletRequest request) {
//        if (!enabled) {
//            return true;
//        }
//        if (excludes == null || excludes.isEmpty()) {
//            return false;
//        }
//        String url = request.getServletPath();
//        for (String pattern : excludes) {
//            Pattern p = Pattern.compile("^" + pattern);
//            Matcher m = p.matcher(url);
//            if (m.find()) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
///**
// * Filters to prevent XSS attacks
// */
//@Component
//public class XssFilter implements Filter {
//    /**
//     * Exclude links
//     */
//    private List<String> excludes = new ArrayList<>();
//
//    /**
//     * xss Filter switch
//     */
//    private boolean enabled = false;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        String tempExcludes = filterConfig.getInitParameter("excludes");
//        String tempEnabled = filterConfig.getInitParameter("enabled");
//        if (StrUtil.isNotEmpty(tempExcludes)) {
//            String[] url = tempExcludes.split(",");
//            Collections.addAll(excludes, url);
//        }
//        if (StrUtil.isNotEmpty(tempEnabled)) {
//            enabled = Boolean.valueOf(tempEnabled);
//        }
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        if (handleExcludeUrl(req)) {
//            chain.doFilter(request, response);
//            return;
//        }
//        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
//        chain.doFilter(xssRequest, response);
//    }
//
//    /**
//     * Judge whether the current path needs to be filtered
//     */
//    private boolean handleExcludeUrl(HttpServletRequest request) {
//        if (!enabled) {
//            return true;
//        }
//        if (excludes == null || excludes.isEmpty()) {
//            return false;
//        }
//        String url = request.getServletPath();
//        for (String pattern : excludes) {
//            Pattern p = Pattern.compile("^" + pattern);
//            Matcher m = p.matcher(url);
//            if (m.find()) {
//                return true;
//            }
//        }
//        return false;
//    }
//}