package test.project.financial_api.expection;

import test.project.financial_api.common.valueobject.BusinessError;

public class TransferAccountsEqualsExceptions extends AbstractBusinessLogicalException {
  public TransferAccountsEqualsExceptions() {
    super(BusinessError.TRANSFER_ACCOUNTS_EQUALS_ERROR);
  }
}
