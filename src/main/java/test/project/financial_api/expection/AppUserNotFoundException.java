package test.project.financial_api.expection;

import static test.project.financial_api.common.valueobject.BusinessError.APP_USER_NOT_FOUND;

public class AppUserNotFoundException extends AbstractBusinessLogicalException {
  public AppUserNotFoundException() {
    super(APP_USER_NOT_FOUND);
  }
}
