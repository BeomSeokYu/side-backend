package com.blws.side.common.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults().excluding(ErrorAttributeOptions.Include.EXCEPTION));
            }
        };
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDenideException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied");
    }

    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse response, CustomException exception) throws IOException {
        response.sendError(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse response, Exception exception) throws IOException {
        log.info("== Handle Exception == [{}] [{}] : {}", exception.getClass(), exception.getMessage(), exception.getStackTrace());
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Something Went Wrong");

    }

}
