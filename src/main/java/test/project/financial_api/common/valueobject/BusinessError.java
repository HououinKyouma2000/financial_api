package test.project.financial_api.common.valueobject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

//TODO вынести в апи словарь
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum BusinessError {
  APP_USER_NOT_FOUND("Пользователь не найден в системе", 1000),
  APP_USER_ALREADY_EXIST("Пользователь уже есть в системе", 1001),
  VALIDATION_ERROR("Ошибка валидации", 1002),
  VALIDATION_ACCOUNT_OWNER_ERROR("Не совпадает владелец счета и клиент запроса", 1003),
  INSUFFICIENT_FUNDS_ERROR("Недостаточно средств для трансфера", 1004),
  TRANSFER_ACCOUNTS_EQUALS_ERROR("Счет отправки и получателя дублируются", 1005),
  ACCOUNT_NOT_FOUND("Счет не найден", 1005),
  
  
  UNKNOWN("Неизвестная ошибка", 9999);
  
  
  String message;
  int code;
}
