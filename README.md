# banksystem
Скрипты базы данных лежит в /src/main/resources/db/migration/V1__init.sql

Для миграций использовал FlyWay

БД для хранения сущности postgreSql

Данные биржевых торгов получаю из https://twelvedata.com/docs#time-series

# Rest API
1.1 Запрос для приема транзакций: POST http://localhost:8181/bank/bank-service/v1/transaction?category=?
Для категорий выбираем либо service либо product. В другом случий получаем badrequest;

Запрос:

![image](https://user-images.githubusercontent.com/98425087/206643793-ede85b40-3e70-41a1-ae5e-313e06ebcd62.png)

Ответ:

![image](https://user-images.githubusercontent.com/98425087/206643949-e62d1434-b6cc-4c4f-a5ff-cd500004e500.png)

Данные в БД:

![image](https://user-images.githubusercontent.com/98425087/206644007-9804254e-3c1e-425e-9a01-b47e345bbfec.png)

1.2 Запрос для установление нового лимита POST http://localhost:8181/bank/client-service/v1/setlimit

Запрос:

![image](https://user-images.githubusercontent.com/98425087/206644321-4dbdd25b-da6f-4e1b-91ec-358fd5dfba87.png)

Ответ:

![image](https://user-images.githubusercontent.com/98425087/206644366-08475cb0-64e1-48d1-9353-2b15a5cbd459.png)

Данные в БД:

![image](https://user-images.githubusercontent.com/98425087/206644409-17623677-2684-457e-8215-6ae73c8e79d2.png)

1.3 Запрос для вывода список транзакций: GET http://localhost:8181/bank/client-service/v1/transactions?account=1200000001

Ответ:

![image](https://user-images.githubusercontent.com/98425087/206644679-0e396c3e-227a-445d-9e25-508d48b77395.png)

1.4 Запрос для вывода списка установленных лимитов: GET http://localhost:8181/bank/client-service/v1/limits?account=1200000001

Ответ:

![image](https://user-images.githubusercontent.com/98425087/206644901-7b7aba2e-da92-4856-ba3b-2f098ebb0c5f.png)

1.5. Запрос для вывода списка транзакций, превысивших лимит: GET http://localhost:8181/bank/client-service/v1/limit-exceeded

Запрос:

![image](https://user-images.githubusercontent.com/98425087/206645424-55bdc702-2add-44fe-bddd-0eee21edb5d4.png)

Ответ:

![image](https://user-images.githubusercontent.com/98425087/206645518-eda6fc58-e8cd-44f1-be39-73c79363e7b2.png)

Данные в БД:

![image](https://user-images.githubusercontent.com/98425087/206645612-c345392e-d996-460e-8691-06dbf5e3c39d.png)
