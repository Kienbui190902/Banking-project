package com.example.Spring.exception;

import com.example.Spring.dto.Api;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;
    public GlobalExceptionHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Api<Map<String, Object>>> handleInsufficientBalance(InsufficientBalanceException ex){
        Locale currentLocale = LocaleContextHolder.getLocale();

        String message = messageSource.getMessage(
                "error.insufficient_balance",
                new Object[]{
                        ex.getSoTaiKhoan(),
                        ex.getSoDuHienTai(),
                        ex.getSoTienYeuCau(),
                        ex.getSoTienConThieu()
                },
                currentLocale);

        Map<String, Object> details = new HashMap<>();
        details.put("soTaiKhoan", ex.getSoTaiKhoan());
        details.put("soDuHienTai", ex.getSoDuHienTai());
        details.put("soTienYeuCau", ex.getSoTienYeuCau());
        details.put("soTienConThieu", ex.getSoTienConThieu());

        Api<Map<String, Object>> response = new Api<>(false, message, details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Api<Map<String, Object>>> handleResourceNotFound(ResourceNotFoundException ex) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String message;
        if (ex.getResourceName() != null) {
            message = messageSource.getMessage(
                    "error.resource_not_found",
                    new Object[]{ex.getResourceName(), ex.getFieldName(), ex.getFieldValue()},
                    currentLocale
            );
        } else {
            message = ex.getMessage();
        }

        Map<String, Object> details = new HashMap<>();
        details.put("resourceName", ex.getResourceName());
        details.put("fieldName", ex.getFieldName());
        details.put("fieldValue", ex.getFieldValue());

        Api<Map<String, Object>> response = new Api<>(false, message, details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Api<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Api<Object> response = new Api<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Api<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        Api<Map<String, String>> response = new Api<>(false, "Dữ liệu gửi lên không hợp lệ!", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Api<Object>> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        Api<Object> response = new Api<>(false, "Hệ thống gặp sự cố, vui lòng thử lại sau!", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}