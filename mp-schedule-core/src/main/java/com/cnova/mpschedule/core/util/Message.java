package com.cnova.mpschedule.core.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface Message {

	String getMessage(String key);
    String getMessage(String key, List objects);
    String getMessage(String key, String locale);
}
