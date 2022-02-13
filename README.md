### Система управления обучением для учебных заведений

###### (версия 0.0.2)

#### Необходимые компоненты для запуска и их рекомендованные версии:

###### 1) Node.Js - v16.13.1

###### 2) Java - v17

###### 3) PostgreSQL - v14.1

#### Запуск:

###### 1) В PostgreSQL создать 2 базы данных: nix-lms и nix-keycloak с дефолтными public схемами

###### 2) Прописать в application.properties приложения server свой username и password для доступа к бд

###### 3) Прописать в keycloak-16.1.1\standalone\configuration\standalone.xml сервера keycloak в данном блоке свой username и password для доступа к бд

    <security>
        <user-name>undefined</user-name>
        <password>undefined</password>
    </security>

###### 4) Для сборки и запуска приложения в корне проекта лежит run-all.bat

###### 4.1) Для запуска сервера Keycloak используется файл run-auth.bat, в котором вызывается standalone.bat для запуска сервера. В случае с Unix-системами нужно заменить его на standalone.sh с сохранением всех параметров

#### P.S:

##### Server:

###### доступен на http://localhost:8080

##### Swagger:

###### доступен на http://localhost:8080/swagger-ui.html

##### Keycloak:

###### доступен на http://localhost:8083/auth

###### учетные данные для доступа к master realm:

###### 1) Логин: lmsadmin

###### 2) Пароль: lmsadmin

##### Client-admin:

###### доступен на http://localhost:4200

###### дефолтные учетные данные для входа:

###### 1) Логин: admin

###### 2) Пароль: admin

##### Client-student:

###### доступен на http://localhost:4201

###### дефолтные учетные данные для входа:

###### 1) Логин: student

###### 2) Пароль: student

##### Client-tutor:

###### client-tutor доступен на http://localhost:4202

###### дефолтные учетные данные для входа:

###### 1) Логин: tutor

###### 2) Пароль: tutor

<h5>Финальный проект, NIX (java-education), Любименко Роман, 2021-2022 гг.<h5>
