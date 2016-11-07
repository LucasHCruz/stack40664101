package com.cnova.mpschedule.core.util.impl;

import com.cnova.mpschedule.core.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
@SuppressWarnings("rawtypes")
public class MessageSourceServiceImpl implements Message {

    @Autowired
    MessageSource messages;

    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, Object[] objects) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, objects, locale);
    }

    @Override
    public String getMessage(String key, Locale locale) {
        return messages.getMessage(key, null, locale);
    }

}
