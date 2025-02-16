package com.blws.side.common.dto;

import com.blws.side.common.exception.CustomException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class ResponseDTO {

    private Map<String, String> data;
    private String resourceType;

    public static ResponseDTO of(Object entity) {
        String className = entity.getClass().getName();
        ResponseDTO responseDTO = builder()
                .data(new HashMap<>())
                .resourceType(className.substring(className.lastIndexOf((".")) + 1))
                .build();
        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                    responseDTO.data.put(field.getName(), value.toString());
                }
            }
        } catch (IllegalAccessException e) {
            throw new CustomException("Create ResponseDTO failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseDTO;
    }

}
