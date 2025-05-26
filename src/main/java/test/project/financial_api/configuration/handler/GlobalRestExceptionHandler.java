package test.project.financial_api.configuration.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import test.project.financial_api.api.dto.ErrorModel;
import test.project.financial_api.api.dto.ValidationErrorModel;
import test.project.financial_api.common.valueobject.BusinessError;
import test.project.financial_api.expection.AbstractBusinessLogicalException;

import java.util.ArrayList;
import java.util.List;

import static test.project.financial_api.common.valueobject.BusinessError.VALIDATION_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GlobalRestExceptionHandler {
  
  //TODO покрыть остальные типы ошибок
  @ExceptionHandler(AbstractBusinessLogicalException.class)
  public ResponseEntity<ErrorModel> handleBusinessLogicalException(final AbstractBusinessLogicalException ex) {
    return ResponseEntity.badRequest().body(
      new ErrorModel(ex.getCode(), ex.getMessage())
    );
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorModel> handleValidationError(final MethodArgumentNotValidException ex) {
    final List<ValidationErrorModel.Error> errors = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
      errors.add(new ValidationErrorModel.Error(error.getField(), error.getDefaultMessage())));
    return ResponseEntity.badRequest().body(
      new ValidationErrorModel(VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getMessage(), errors)
    );
  }
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorModel> handleAllExceptions(final Exception ex, final WebRequest request) {
    log.error(ex.getMessage(), ex);
    return ResponseEntity.internalServerError().body(
      new ErrorModel(BusinessError.UNKNOWN.getCode(), BusinessError.UNKNOWN.getMessage()));
  }
  
}
