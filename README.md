# Learning Management System

The final project of the Java course from NIX Solutions & A-Level.

## Requirements

| Name       | Version |
|:-----------|:--------|
| Java       | 17      |
| Node.Js    | 16.13.1 |
| PostgreSQL | 14.1    |

## Launching

1. Create 2 databases in PostgreSQL: nix-lms and nix-keycloak with default public schemas.
2. Set your username and password in application.properties of the server application to access the database.
3. Register in the directory keycloak-16.1.1\standalone\configuration\standalone.xml of the keycloak server your
   username and password to access the database.

> Example:

```
    <security>
        <user-name>username</user-name>
        <password>password</password>
    </security>
```

4. To build and run the application in the root of the project, run the following file run-all.bat.

> To start the Keycloak server, use the run-auth.bat file, which calls standalone.bat to start the server. In the case
> of Unix systems, you need to replace it with standalone.sh with all parameters saved.

## Usage

1. Back-end

```
http://localhost:8080
```

2. Swagger

```
http://localhost:8080/swagger-ui.html
```

3. Keycloak

```
http://localhost:8083/auth
```

Default credentials for master realm

* Login => lmsadmin
* Password => lmsadmin

4. Admin front-end

```
http://localhost:4200
```

Default credentials for admin account

* Login => admin
* Password => admin

4. Student front-end

```
http://localhost:4201
```

Default credentials for student account

* Login => student
* Password => student

5. Tutor front-end

```
http://localhost:4202
```

Default credentials for tutor account

* Login => tutor
* Password => tutor
