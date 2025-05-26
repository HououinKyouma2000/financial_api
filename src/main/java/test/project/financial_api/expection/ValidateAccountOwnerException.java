package test.project.financial_api.expection;

import static test.project.financial_api.common.valueobject.BusinessError.VALIDATION_ACCOUNT_OWNER_ERROR;

public class ValidateAccountOwnerException extends AbstractBusinessLogicalException {
  public ValidateAccountOwnerException() {
    super(VALIDATION_ACCOUNT_OWNER_ERROR);
  }
}
