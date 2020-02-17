# login-jwt

## se ha usado
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
- [Register User](#register user)
    - [Register User inautorizdo](#Register User inautorizdo)
    - [Register User](#register-user)


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
##Register User Inautorizado
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
```
{
    "mensaje": "No esta autorizado."
}
```

##Registro de usario
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
Body
```
{
    "id": 2,
    "name": "Juan Rodriguez",
    "isactivo": true,
    "update": "2020-02-17T11:29:11.268+0000",
    "created": "2020-02-17T11:29:11.268+0000",
}
```
