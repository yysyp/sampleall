package ps.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import ps.demo.common.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public ResponseEntity<MyBaseResponse> constructResponseEntity(CodeEnum codeEnum, Exception e) {
        MyBaseResponse errorResponse = MyBaseResponse.of(codeEnum);
        errorResponse.setDetail(e.getMessage());
        HttpStatus httpStatus = HttpStatus.valueOf(codeEnum.getHttpCode());
        return new ResponseEntity<MyBaseResponse>(errorResponse, httpStatus);
    }

    public ResponseEntity<MyBaseResponse> constructResponseEntity(Exception e) {
        if (e instanceof MyClientErrorException) {
            return constructResponseEntity(((MyClientErrorException)e).getCodeEnum(), e);
        } else if (e instanceof MyServerErrorException) {
            return constructResponseEntity(((MyServerErrorException)e).getCodeEnum(), e);
        }
        return constructResponseEntity(CodeEnum.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(value = ServletRequestBindingException.class)
    public ResponseEntity<MyBaseResponse> handleException(ServletRequestBindingException e) {
        log.error("ServletRequestBindingException handling, message={}", e.getMessage(), e);
        return constructResponseEntity(CodeEnum.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<MyBaseResponse> handleException(NoHandlerFoundException e) {
        log.error("NoHandlerFoundException handling, message={}", e.getMessage(), e);
        return constructResponseEntity(CodeEnum.BAD_REQUEST, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyBaseResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("MethodArgumentNotValidException handling, message={}", ex.getMessage(), ex);
        MyBaseResponse errorResponse = MyBaseResponse.of(CodeEnum.BAD_REQUEST);
        HttpStatus httpStatus = HttpStatus.valueOf(CodeEnum.BAD_REQUEST.getHttpCode());
        errorResponse.setMessage(errors.toString());
        return new ResponseEntity<MyBaseResponse>(errorResponse, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyBaseResponse> handleThrowable(Exception e) {
        log.error("Exception handleThrowable, message={}", e.getMessage(), e);
        return constructResponseEntity(e);
    }

}