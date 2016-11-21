package com.cnova.mpschedule.core.dto.validator;

import com.cnova.mpschedule.core.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonsValidator {

    @Autowired
    Message message;

    public boolean objectIsNotNull(Object object, String objeto, List<String> errors) {
        boolean isNotNull = true;

        if(object == null){
            errors.add(message.getMessage("required.object", objeto));
            isNotNull = false;
        }
        return isNotNull;
    }

}
