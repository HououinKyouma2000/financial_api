Есть два способа запуска. Первый с помощью docker compose starter, второй через полный запуск в контейнерах
1)Первый способ запуска
./gradlew bootRun


2)Второй способ запуска 
./gradlew bootJar
docker-compose -f full-compose.yaml up


Open Api
http://localhost:8080/swagger/swagger-ui/index.html#/
Порядок действий 
1) создать пользователя
2) login
3) вставка токен
4) дальнейшие действия


Тестовые клиенты с паролями Qwerty123
1) Testuser 
2) Testusersecond

Соответсвенно счета
1) 22222222-2222-2222-2222-222222222222  баланс 500
2) 33333333-3333-3333-3333-333333333333   баланс 600

Тестовые даты для двух транзакций
1) 2024-04-01T10:00:00
2) 2024-04-02T15:30:00


Описание решений
1) слабое изолирование видимости данных в api, для упрощения контрактов и по требованию получаения всех трансферов
2) Логика трансфероф через порядковую блокировки записей, чтоб не усложнять логику через высокий уровень изоляции или Optimistic @Version с @Retryable
По идее мы блокируем запись account, потому если мы работаем с appUser тоже с pessimistic,
то они могут на друг друга влиять, потому при дальнейшем имеет смысл возможно убрать отношения или сделать через другую реализацию,
например через ту же изоляцию
3) Unit тестов нет, но интеграционные тесты покрывают в целом end to end логику, потому скипнул
4) У пользователя пока с счетами отношение toOne, но местами логика реализована на вырост
5) Оставльные моменты которые можно развить выделены в //TODO
6) Запросы на получение коллекций реализованы через Page, так как в целом так лучше :-)
