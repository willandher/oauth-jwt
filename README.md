# spring-security-jwt

## Technology stack
- Java 8
- Spring Boot
- Spring Data
- Spring Security
- H2
- Maven

## API Documentation

- [Login](#Login)
    - [Success](#login-success)
    - [Email not found](#login email not found)
    - [Unauthorized](#login-unauthorized)
- [Register User](#register user)
    - [Success](#success)
    - [Email already exists](#register-user-email-already-exists)
    - [Bad request](#register-user-bad-request)


##Login

```
POST: http://localhost:5000/api/auth/login
```

Header
```
Content-Type: application/json
```

Body 
``` 
{
         	"email": "willandherg@gmail.com",
         	"password": "Will22"
         }
```


##Login-success 
Status: 200
Body
```
{
    "id": 1,
    "name": "Willandher Goyo",
    "isactivo": true,
    "update": "2020-02-11T17:48:43.763+0000",
    "lastLogin": "2020-02-11T17:48:43.763+0000",
    "created": "2020-02-11T17:48:43.763+0000",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTgxNDQzMzM3LCJleHAiOjE1ODIwNDgxMzd9.FvSgLAVcmZAeId7rakBs0lFhD3w3IO-YADd522zT2oPFjsKctreBDSrMGAyYIJH9tP6LNz8Ss8LRbQMEsOA5Dw"
}
```
##login email not found
Body 
``` 
{
         	"email": "willandhergg@gmail.com",
         	"password": "Will22"
         }
```
Body
Status: 404
```
{
  "message": "User no encontrado [email: willandhergg@gmail.com]"
}

```
##Register User unauthorized
```
POST: http://localhost:5000/api/auth/register-user
```
Header
```
Content-Type: application/json
```
Body
```
{
	"name": "Juan Rodriguez",
	"email": "juanrodrigez@gmail.com",
	"password": "Will22",
	"phones": [
	{
	"number": "1234567",
	"citycode": "1",
	"countrycode": "57"
	}
	]
}
```
Body 
Status 401

##REgistro de usario
Loguearse
```
POST: http://localhost:5000/api/auth/login
```
Header
```
Content-Type: application/json
```

Body 
``` 
{
         	"email": "willandherg@gmail.com",
         	"password": "Will22"
         }
```
```
{
    "id": 1,
    "name": "Willandher Goyo",
    "isactivo": true,
    "update": "2020-02-11T17:48:43.763+0000",
    "lastLogin": "2020-02-11T17:48:43.763+0000",
    "created": "2020-02-11T17:48:43.763+0000",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTgxNDQzMzM3LCJleHAiOjE1ODIwNDgxMzd9.FvSgLAVcmZAeId7rakBs0lFhD3w3IO-YADd522zT2oPFjsKctreBDSrMGAyYIJH9tP6LNz8Ss8LRbQMEsOA5Dw"
}
```
Crear Usuario
```
POST: http://localhost:5000/api/auth/register-user
```
Header
```
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTgxNDQzMzM3LCJleHAiOjE1ODIwNDgxMzd9.FvSgLAVcmZAeId7rakBs0lFhD3w3IO-YADd522zT2oPFjsKctreBDSrMGAyYIJH9tP6LNz8Ss8LRbQMEsOA5Dw
```
Body 
```
{
	"name": "Juan Rodriguez",
	"email": "juanrodrigez@gmail.com",
	"password": "Will22",
	"phones": [
	{
	"number": "1234567",
	"citycode": "1",
	"countrycode": "57"
	}
	]
}
```