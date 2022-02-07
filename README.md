### Система управления обучением для учебных заведений

###### (версия 0.0.1)

#### Необходимые компоненты для запуска и их рекомендованные версии:

###### 1) Node.Js - v16.13.1

###### 2) Java - v17

###### 3) PostgreSQL - v14.1

#### Запуск:

###### 1) Создать 2 базы данных: nix-lms и nix-keycloak с дефолтными public схемами

###### 2) Прописать в application.properties приложения server свой username и password для доступа к бд

###### 3) Прописать в keycloak-16.1.1\standalone\configuration\standalone.xml сервера keycloak в этом блоке свой username и password для доступа к бд

    <security>
        <user-name>undefined</user-name>
        <password>undefined</password>
    </security>

###### 4) Неободимо запустить сервер Keycloak. Для этого в корне проекта есть скрипт run-auth.bat. В случае с Linux нужно запустить standalone.sh со всеми параметрами с run-auth.bat

###### 5) Запустить server приложения

###### 6) Запустить по выбору клиентские приложения client-admin, client-student, client-tutor

###### server доступен на http://localhost:8080

###### swagger доступен на http://localhost:8080/swagger-ui.html

###### keycloak доступен на http://localhost:8083/auth

###### client-admin доступен на http://localhost:4200

###### client-student доступен на http://localhost:4201

###### client-tutor доступен на http://localhost:4202

<h5>Финальный проект, NIX (java-education), Любименко Роман, 2021-2022 гг.<h5>
