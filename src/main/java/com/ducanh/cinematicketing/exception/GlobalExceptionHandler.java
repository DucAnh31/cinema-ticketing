package com.ducanh.cinematicketing.exception;

import com.ducanh.cinematicketing.common.response.ApiResponse;
import com.ducanh.cinematicketing.common.response.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException e) {
        log.error("ApiException exception: ", e);
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(ApiResponse.error(
                        e.getMessage(), ErrorDetail.of(e.getErrorCode().name())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException exception: ", e);
        List<ErrorDetail.FieldError> fieldErrors = new ArrayList<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.add(toFieldError(fieldError));
        }

        ErrorDetail error = ErrorDetail.of(ErrorCode.VALIDATION_ERROR.name(), fieldErrors);
        return ResponseEntity.status(ErrorCode.VALIDATION_ERROR.getStatus())
                .body(ApiResponse.error(ErrorCode.VALIDATION_ERROR.getDefaultMessage(), error));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException e) {
        log.error("Uncategorized exception: ", e);

        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatus())
                .body(ApiResponse.error(
                        ErrorCode.UNCATEGORIZED_EXCEPTION.getDefaultMessage(),
                        ErrorDetail.of(ErrorCode.UNCATEGORIZED_EXCEPTION.name())));
    }

    private ErrorDetail.FieldError toFieldError(FieldError fe) {
        return new ErrorDetail.FieldError(fe.getField(), fe.getDefaultMessage());
    }
}
