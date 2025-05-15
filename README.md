# Sprint Web App

## Описание

**Sprint Web App** — это простое веб-приложение, разработанное с использованием Spring Boot. Оно демонстрирует базовые
возможности работы с MVC (Model-View-Controller), включая отображение списка пользователей и просмотр информации о
конкретном пользователе.

## Функционал

- Отображение списка всех пользователей.
- Просмотр детальной информации о пользователе по его ID.

## Технологии

- **Java 17**
- **Spring Boot 3**
- **Thymeleaf** — для шаблонизации.
- **Maven** — для управления зависимостями.
- **JUnit 5** — для тестирования.

## Структура проекта

- `src/main/java/com/example/sprintwebapp/controller` — контроллеры для обработки HTTP-запросов.
- `src/main/java/com/example/sprintwebapp/DAO` — слой доступа к данным.
- `src/main/java/com/example/sprintwebapp/Model` — модель данных.
- `src/main/resources/templates` — HTML-шаблоны для отображения данных.

## Установка и запуск

1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/TadoHopsky/sprint-web-app.git
   ```
2. Перейдите в директорию проекта:
   ```bash
   cd sprint-web-app
   ```
3. Соберите проект с помощью Maven:
   ```bash
   mvn clean install
   ```
4. Запустите приложение:
   ```bash
   mvn spring-boot:run
   ```

## Использование

- Перейдите по адресу `http://localhost:8080/people/all`, чтобы увидеть список всех пользователей.
- Нажмите на имя пользователя, чтобы перейти на страницу с его подробной информацией.

## Примеры

### Список пользователей

Пример страницы `/people/all`:

- **John Doe**
- **Tado Hopsky**
- **Masha Ivanova**

### Детальная информация

Пример страницы `/people/{id}`:

- Имя: John Doe
- Email: john123123@mail.ru
- Адрес: Moscow

## Тестирование

Для запуска тестов выполните:

```bash
mvn test
```

## Автор

**Tado Hopsky**

## Лицензия

Этот проект распространяется под лицензией MIT.

```