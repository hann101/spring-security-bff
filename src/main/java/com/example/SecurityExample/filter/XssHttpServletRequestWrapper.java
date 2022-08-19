//package com.example.SecurityExample.filter;
//
//
//import cn.hutool.core.util.EscapeUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//
///**
// * XSS Filtering treatment
// */
//public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
//
//    /**
//     * Description: constructor
//     *
//     * @param request Request object
//     */
//    public XssHttpServletRequestWrapper(HttpServletRequest request) {
//        super(request);
//    }
//
//
//    @Override
//    public String getHeader(String name) {
//        String value = super.getHeader(name);
//        return EscapeUtil.escape(value);
//    }
//
//    //Override getParameter
//    @Override
//    public String getParameter(String name) {
//        String value = super.getParameter(name);
//        return EscapeUtil.escape(value);
//    }
//
//    //Override getParameterValues
//    @Override
//    public String[] getParameterValues(String name) {
//        String[] values = super.getParameterValues(name);
//        if (values != null) {
//            int length = values.length;
//            String[] escapseValues = new String[length];
//            for (int i = 0; i < length; i++) {
//                escapseValues[i] = EscapeUtil.escape(values[i]);
//            }
//            return escapseValues;
//        }
//        return super.getParameterValues(name);
//    }
//}