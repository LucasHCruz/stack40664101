package com.cnova.mpschedule.core.service.mock;

import org.springframework.stereotype.Service;

import com.cnova.mpschedule.core.service.RightNowService;
import com.cnova.mpschedule.core.util.helper.FF4JHelper;

@Service(FF4JHelper.RIGHT_NOW_SERVICE_ALTERNATIVE)
public class RightNowServiceMock implements RightNowService {

    @Override
    public String teste() {
        return "Implementação MOCK";
    }

}

