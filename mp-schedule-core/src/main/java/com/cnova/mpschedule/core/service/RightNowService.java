package com.cnova.mpschedule.core.service;

import org.ff4j.aop.Flip;

import com.cnova.mpschedule.core.util.helper.FF4JHelper;

@Flip(name = FF4JHelper.RIGHT_NOW_SERVICE, alterBean = FF4JHelper.RIGHT_NOW_SERVICE_ALTERNATIVE)
public interface RightNowService {

    public String teste();
}
