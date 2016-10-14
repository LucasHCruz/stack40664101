package com.cnova.mpschedule.core.util.impl;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.cnova.mpschedule.core.util.Message;


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
    public String getMessage(String key, List objects) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, objects.toArray(), locale);
    }

    @Override
    public String getMessage(String key, String locale) {
        return messages.getMessage(key, null, LocaleUtils.toLocale(locale));
    }

}
