package com.in4java.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalUtil {

    private static MessageSource messageSource;

    public LocalUtil(MessageSource messageSource) {
        LocalUtil.messageSource = messageSource;
    }

    /**
     获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }

}