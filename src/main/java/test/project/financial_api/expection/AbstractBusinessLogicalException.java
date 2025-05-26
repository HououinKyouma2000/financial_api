package test.project.financial_api.expection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import test.project.financial_api.common.valueobject.BusinessError;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class AbstractBusinessLogicalException extends RuntimeException {
  String message;
  int code;
  
  protected AbstractBusinessLogicalException(final BusinessError error) {
    this.code = error.getCode();
    this.message = error.getMessage();
  }
  
  protected AbstractBusinessLogicalException(final int code, final String message) {
    this.code = code;
    this.message = message;
  }
}
