package test.project.financial_api.expection;


import test.project.financial_api.common.valueobject.BusinessError;

public class InsufficientFundsException extends AbstractBusinessLogicalException {
  public InsufficientFundsException() {
    super(BusinessError.INSUFFICIENT_FUNDS_ERROR);
  }
}
