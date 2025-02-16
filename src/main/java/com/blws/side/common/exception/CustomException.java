package com.blws.side.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
public class CustomException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

    private String errorCode;
    private String details;
    private String path;
    private String method;
    private Throwable cause;
    private String requestId;
    private String userMessage;


    /**
     * Constructs a new CustomException with the specified message and HTTP status.
     *
     * @param message   the detail message
     * @param httpStatus the HTTP status code
     */
    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs a new CustomException with the specified message, HTTP status, and error code.
     *
     * @param message   the detail message
     * @param httpStatus the HTTP status code
     * @param errorCode  the error code
     */
    public CustomException(String message, HttpStatus httpStatus, String errorCode) {
        this(message, httpStatus);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new CustomException with the specified message, HTTP status, error code, and details.
     *
     * @param message   the detail message
     * @param httpStatus the HTTP status code
     * @param errorCode  the error code
     * @param details    additional details about the exception
     */
    public CustomException(String message, HttpStatus httpStatus, String errorCode, String details) {
        this(message, httpStatus, errorCode);
        this.details = details;
    }

    /**
     * Constructs a new CustomException with the specified message, HTTP status, error code, details, path, and method.
     *
     * @param message   the detail message
     * @param httpStatus the HTTP status code
     * @param errorCode  the error code
     * @param details    additional details about the exception
     * @param path       the request path where the exception occurred
     * @param method     the HTTP method of the request
     */
    public CustomException(String message, HttpStatus httpStatus, String errorCode, String details, String path, String method) {
        this(message, httpStatus, errorCode, details);
        this.path = path;
        this.method = method;
    }

    /**
     * Constructs a new CustomException with the specified message, HTTP status, error code, details, path, method, and cause.
     *
     * @param message   the detail message
     * @param httpStatus the HTTP status code
     * @param errorCode  the error code
     * @param details    additional details about the exception
     * @param path       the request path where the exception occurred
     * @param method     the HTTP method of the request
     * @param cause      the cause of the exception
     */
    public CustomException(String message, HttpStatus httpStatus, String errorCode, String details, String path, String method, Throwable cause) {
        this(message, httpStatus, errorCode, details, path, method);
        this.cause = cause;
    }

    /**
     * Constructs a new CustomException with the specified message, HTTP status, error code, details, path, method, cause, and requestId.
     *
     * @param message   the detail message
     * @param httpStatus the HTTP status code
     * @param errorCode  the error code
     * @param details    additional details about the exception
     * @param path       the request path where the exception occurred
     * @param method     the HTTP method of the request
     * @param cause      the cause of the exception
     * @param requestId  the ID of the request that caused the exception
     */
    public CustomException(String message, HttpStatus httpStatus, String errorCode, String details, String path, String method, Throwable cause, String requestId) {
        this(message, httpStatus, errorCode, details, path, method, cause);
        this.requestId = requestId;
    }

    /**
     * Constructs a new CustomException with the specified message, HTTP status, error code, details, path, method, cause, requestId, and userMessage.
     *
     * @param message     the detail message
     * @param httpStatus   the HTTP status code
     * @param errorCode    the error code
     * @param details      additional details about the exception
     * @param path         the request path where the exception occurred
     * @param method       the HTTP method of the request
     * @param cause        the cause of the exception
     * @param requestId    the ID of the request that caused the exception
     * @param userMessage  the user-friendly message
     */
    public CustomException(String message, HttpStatus httpStatus, String errorCode, String details, String path, String method, Throwable cause, String requestId, String userMessage) {
        this(message, httpStatus, errorCode, details, path, method, cause, requestId);
        this.userMessage = userMessage;
    }

}
