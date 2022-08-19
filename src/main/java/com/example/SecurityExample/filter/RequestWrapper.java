package com.example.SecurityExample.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;
import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RequestWrapper extends HttpServletRequestWrapper {
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.println("values = " + values[i]);
            encodedValues[i] = clean(values[i]);
        }
        return encodedValues;
    }
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return clean(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return clean(value);

    }

    /*
    패턴 검증
     */
    private static interface Patterns {
        //		 javascript tags and everything in between
        public static final Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
        public static final Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL);
        public static final Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
        public static final Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
        public static final Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
        public static final Pattern WHITESPACE = Pattern.compile("\\s\\s+");
    }

    /**
     * Clean the HTML input.
     * SQL Injection 취약점 or 크로스 사이트 스크립팅 취약점
     */
    public static String clean(String value) {
        if (value == null) {
            return null;
        }

        System.out.println("XSS Filter before : " + value);
        Matcher m;

        m = Patterns.SCRIPTS.matcher(value);
        value = m.replaceAll("");
        m = Patterns.STYLE.matcher(value);
        value = m.replaceAll("");
        m = Patterns.TAGS.matcher(value);
        value = m.replaceAll("");
        m = Patterns.ENTITY_REFS.matcher(value);
        value = m.replaceAll("");
        m = Patterns.WHITESPACE.matcher(value);
        value = m.replaceAll(" ");

        System.out.println("XSS Filter after : " + value);

        return value;
    }


}
