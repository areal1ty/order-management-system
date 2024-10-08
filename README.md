# Order Management Service
## 📋 Описание проекта
Order Management Service — это микросервис на базе Spring Boot, предназначенный для управления заказами в электронной коммерции. Основная цель проекта — предоставление REST API для операций с заказами, доставками, платежами и позициями заказа. Проект реализован на языке Kotlin с применением архитектурных паттернов и включает тестирование всех компонентов приложения.

## 📦 Основная функциональность
### Order:
Создание нового заказа.
Получение информации о заказе по его идентификатору.
Обновление данных заказа.
Удаление заказа.
### Delivery:
Получение списка всех доставок.
Получение информации о доставке по идентификатору.
Создание новой доставки.
Обновление существующей доставки.
### Item:
Создание позиции заказа, привязанной к конкретному заказу.
Получение списка всех позиций заказа.
Обновление данных позиции.
Удаление позиции заказа.
### Payment:
Создание нового платежа.
Получение информации о платеже по идентификатору.
Обновление статуса платежа.
Удаление платежа.
## 🔧 Технические особенности
Kotlin как основной язык для написания логики микросервиса.
Spring Boot — основная структура приложения, обеспечивающая удобную настройку и разработку.
Spring Data JPA для взаимодействия с базой данных.
Flyway для управления миграциями базы данных и поддержания синхронизации схемы.
JUnit 5 и MockMvc для написания модульных и интеграционных тестов.
REST API с поддержкой различных CRUD-операций для каждой сущности.
DTO (Data Transfer Object) — использование DTO для передачи данных между клиентом и сервером, минимизация утечек внутренней логики приложения.
Mapper-ы для преобразования между DTO и сущностями базы данных.
Логирование с использованием Kotlin Logging для контроля выполнения операций.
## ⚙️ Профили (dev/prod)
Проект поддерживает переключение между профилями dev и prod:

application-dev.yml:

Используется для локальной разработки и тестирования.
Настройки базы данных H2 (in-memory).
Автоматическое создание и инициализация схемы.
application-prod.yml:

Используется для продакшн-среды.
Настройки подключения к PostgreSQL.
Миграции Flyway для контроля изменений схемы базы данных.
### Как переключить профиль:

Установить активный профиль можно с помощью параметра запуска:
`bash`
java -jar order-management-service.jar --spring.profiles.active=dev
или указав в конфигурации IDE spring.profiles.active=prod,
или при запуске mvn spring-boot:run -Dspring.profiles.active=dev

## 📋 Доступные эндпоинты
### OrderController

GET /public/order — Получить список всех заказов.

GET /public/order/{id} — Получить заказ по идентификатору.

POST /public/order — Создать новый заказ.

PUT /public/order/{id} — Обновить существующий заказ.

DELETE /public/order/{id} — Удалить заказ по идентификатору.
### DeliveryController

GET /public/delivery — Получить список всех доставок.

GET /public/delivery/{id} — Получить информацию о доставке по идентификатору.

POST /public/delivery — Создать новую доставку.

PUT /public/delivery/{id} — Обновить информацию о доставке.
### ItemController

GET /public/item — Получить список всех позиций заказа.

GET /public/item/{id} — Получить информацию о позиции заказа по идентификатору.

POST /public/item — Создать новую позицию заказа, привязав её к заказу.

PUT /public/item/{id} — Обновить информацию о позиции заказа.

DELETE /public/item/{id} — Удалить позицию заказа.
### PaymentController

POST /public/payment — Создать новый платеж.

GET /public/payment/{id} — Получить информацию о платеже по идентификатору.

PUT /public/payment/{id} — Обновить информацию о платеже.

PUT /public/payment/{id}/status — Обновить статус платежа.

DELETE public/payment/{id} — Удалить платеж.
## 🧩 Структура проекта
`bash`
├── src

│   ├── main

│   │   ├── kotlin

│   │   │   └── com.ecom.tech.ordermanagementservice

│   │   │       ├── controller       # REST-контроллеры

│   │   │       ├── service          # Бизнес-логика и сервисные слои

│   │   │       ├── repository       # Репозитории Spring Data JPA

│   │   │       ├── model            # DTO, мапперы и сущности базы данных

│   │   ├── resources

│   │   │   ├── application-dev.yml  # Конфигурация для dev-среды

│   │   │   └── application-prod.yml # Конфигурация для prod-среды

├── test

│   ├── kotlin

│   │   └── com.ecom.tech.ordermanagementservice

│   │       ├── service              # Тесты сервисов

│   │       └── controller           # Тесты контроллеров

└── README.md                         # Файл документации
