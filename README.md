# Проверь курс валют перед отпуском!
![image](./image/3.jpg )

## Описание проекта
- Сервис предоставляет актуальные курсы валют, позволяя пользователям быстро получать необходимую информацию перед поездками за границу.

## Функционал сервиса
- 💱 Получение текущих курсов валют: Быстрый доступ к актуальным данным о курсах.
- 🌍 Поддержка различных валют: Работа с множеством валют для удобства пользователей.
- 🔍 Запрос информации по валютам: Возможность получения информации по ID и числовому коду валют.

## Используемые технологии
- Spring Boot 2.7
- Maven 3
- Lombok
- Mapstruct
- Liquibase
- PostgreSQL

# Требования

### JDK 17

- Проект использует синтаксис Java 17. Для локального запуска вам потребуется установленный JDK 17.

### Docker
- Для запуска проекта требуется установленный и работающий Docker. Для работы с базой данных (PostgreSQL) необходимо запустить соответствующий сервис в Docker.

### Подключение к интернету
- Подключение к интернету необходимо для получения актуальных курсов валют.

## Полезные команды

### Docker
- Убедитесь, что Docker установлен и запущен. 
- Необходимо указать Ваши параметры подключения ( username: ******  password:****** ) в **application.yaml**
- Для работы с базой данных (Postgresql) нужно запустить соответствующий контейнер.

```bash
cd docker
```
```bash
docker-compose up
```
- Или, альтернативно:
```bash
docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=postgres -d postgres
```
- Запуск и  развертывание приложения.

```bash
mvn package
```
```bash
docker build -t romakat77/exchange:latest .
```
### IntelliJ IDEA

Запустите main метод класса Application

### Пример кода для выполнения запроса с использованием OkHttpClient:

Получить информацию о курсах валют

```bash
OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "");
Request request = new Request.Builder()
  .url("http://localhost:8080/api/currency")
  .method("GET", body)
  .build();
Response response = client.newCall(request).execute();
```

Получение Валюты по id

```bash
OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/currency/1333")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String body = response.body().string();
            } else {
                System.out.println("Request failed with status code: " + response.code());
            }
        }
    }
```

Получение Валюты  по числовому коду

```bash
OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = RequestBody.create(mediaType, "");
Request request = new Request.Builder()
  .url("http://localhost:8080/api/currency/convert?currencyCode=840")
  .method("GET", body)
  .build();
Response response = client.newCall(request).execute();
```


## Database:
- Postgresql


![image](./image/1.png )
