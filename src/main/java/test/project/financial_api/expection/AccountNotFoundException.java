package test.project.financial_api.expection;

import test.project.financial_api.common.valueobject.BusinessError;

public class AccountNotFoundException extends AbstractBusinessLogicalException {
  public AccountNotFoundException() {
    super(BusinessError.ACCOUNT_NOT_FOUND);
  }
}
