package test.project.financial_api.expection;

import static test.project.financial_api.common.valueobject.BusinessError.APP_USER_ALREADY_EXIST;

public class AppUserAlreadyExistException extends AbstractBusinessLogicalException {
  public AppUserAlreadyExistException() {
    super(APP_USER_ALREADY_EXIST);
  }
}
