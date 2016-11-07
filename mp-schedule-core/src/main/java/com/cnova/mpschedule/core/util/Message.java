package com.cnova.mpschedule.core.util;

import java.util.Locale;

@SuppressWarnings("rawtypes")
public interface Message {

	String getMessage(String key);
    String getMessage(String key, Object... objects);
    String getMessage(String key, Locale locale);
}
