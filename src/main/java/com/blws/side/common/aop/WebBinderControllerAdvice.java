package com.blws.side.common.aop;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class WebBinderControllerAdvice {

    // WebDataBinder 방식을 Bean -> Field 로 변경하여 @ModelAttribute에서도 Setter 없이 주입 가능하도록 변경
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

}
