# co2 Levels API Readme File

## DATABASE 

In this API project I used sqlite so there is no need to connect to a specific database, a file /database.db is created by default (when it does not exist), if you wish deleting the whole database of the project you can delete the file /database.db and another one will be created automatically during the next launch. 

## Starting the project

This project runs on the port localhost 8080

## Authentication

I used JWT base authentication in order to login call the POST API /auth/login for example:

curl -X POST "http://localhost:8080/auth/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"password\", \"username\": \"barcelonaadmin\"}"

The API will respond with a JWT token for example :
{
"token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5ODI4NzM5LCJleHAiOjE2NDk4NTc1Mzl9.UuOMDQ3qZ5p8cDvgBP7BcRNnj0eRc4EN-eP5Ph3VN3Pg41vgMWnGSwOfeO42US8Nd2lU2c9TEt9wzVeK7h8qqA"
}

All the rest of the endpoints in this project are secured and require authentication using the jwt token for example the endpoint : /api/co2Level/all
curl -X GET "http://localhost:8080/api/co2Level/all" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5ODI4NzM5LCJleHAiOjE2NDk4NTc1Mzl9.UuOMDQ3qZ5p8cDvgBP7BcRNnj0eRc4EN-eP5Ph3VN3Pg41vgMWnGSwOfeO42US8Nd2lU2c9TEt9wzVeK7h8qqA"


## Endpoint Definition

There are multiple ways to run the backend. For development, you can use your favorite IDE and run the
`com.hexa.park.HexaParkApIsApplication.java `. As soon as your code compiles, Spring Boot DevTools will reload the code. <br/>

You can also run the application using Maven. <br/>

```bash
$ cd backend
$  mvn spring-boot:run 
```

## Swagger Usage

localhost:8080/swagger-ui.html

```
$ cd frontend
$ npm install
```

Once the above command finishes, you can start the frontend using the `ng serve` command.

<br/>
## Staging environment:<br/>
Git Branch: staging-hexapark<br/>
 <br/> 


## usernames: <br/>
Database name:hexa_park <br/>
Default admin:hexapark@hexatribe.com password:123123 <br/>
Files location:Files are by default uploaded under the folder "uploads" located in the project directory (please note in staging&production environment "uploads" folder is in "C:/Windows/uploads" as it is the default backend jar NSSM service created by the pipeline). <br/>
Logs:Backend loggs at the file "hexapark-logs.log" located as the same directory of the folder "uploads" <br/>


<br/>

## default data: <br/>
Usernames:

Barcelona: Gràcia, Eixample<br/>
Wien: Währing, Penzing<br/>
München: Maxvorstadt<br/>


<br/>

## Github Repository for hexapark mobile version: <br/>
Repo: https://github.com/houssembahbah-hexatribe/hexapark-mobile <br/>


## Example of a CO2 reading to a given customer account

This project runs on the port localhost 8080





