//package com.example.SecurityExample.filter;
//
//
//import cn.hutool.core.util.StrUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.DispatcherType;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author xssfilter to configure
// */
//@Configuration
//public class XssFilterConfig {
//    @Value("${xss.enabled}")
//    private String enabled;
//
//    @Value("${xss.excludes}")
//    private String excludes;
//
//    @Value("${xss.urlPatterns}")
//    private String urlPatterns;
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Bean
//    public FilterRegistrationBean xssFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
////        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new XssFilter());
//        //Add filter path
////        registration.addUrlPatterns(StrUtil.split(urlPatterns, ","));
//        registration.setName("xssFilter");
//        registration.setOrder(Integer.MAX_VALUE);
//        //Set initialization parameters
//        Map<String, String> initParameters = new HashMap<String, String>();
//        initParameters.put("excludes", excludes);
//        initParameters.put("enabled", enabled);
//        registration.setInitParameters(initParameters);
//        return registration;
//    }
//
//    /**
//     * Filter json type
//     *
//     * @param builder
//     * @return
//     */
//    @Bean
//    @Primary
//    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        //Parser
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        //Register xss parser
//        SimpleModule xssModule = new SimpleModule("XssStringJsonDeserializer");
//
//        //Just select one of the input and output parameters for filtering. There is no need to add both
//        //In order to unify with XssHttpServletRequestWrapper, it is recommended to handle the input parameters
//        //Register parameter escape
//        xssModule.addDeserializer(String.class, new XssStringJsonDeserializer());
//        //Register parameter escape
//        //xssModule.addSerializer(new XssStringJsonSerializer());
//        objectMapper.registerModule(xssModule);
//        //return
//        return objectMapper;
//    }
//}
