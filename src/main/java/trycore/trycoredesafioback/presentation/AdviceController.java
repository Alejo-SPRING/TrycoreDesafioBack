package trycore.trycoredesafioback.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import trycore.trycoredesafioback.application.dto.ApiExceptionDTO;
import trycore.trycoredesafioback.domain.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> validations = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validations.put(error.getField(), error.getDefaultMessage());
        });
        ApiExceptionDTO apiExceptionDTO = ApiExceptionDTO.builder().validations(validations)
                .source(ex.getTitleMessageCode()).build();
        return new ResponseEntity<>(apiExceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionDTO> handleApiException(ApiException ex) {
        return new ResponseEntity<>(ApiExceptionDTO.builder().error(ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()).build(), ex.getSource().getStatus());
    }
}
