package com.zf.common.argumentresolver;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameter().getType() == LocalDateTime.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String value = webRequest.getParameter(parameter.getParameter().getName());
        if (!Strings.isBlank(value)) {
            return LocalDateTime.parse(value, DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("UTC")));
        }

        return null;
    }
}
