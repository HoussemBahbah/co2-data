# co2 Levels API Readme File

## Database

In this API project  SQLite is used to store the data, there is no need to connect to a specific database, a file /database.db is automatically created by default (when it does not exist), if you wish deleting the whole database of the project you can delete the file /database.db and another one will be created automatically during the next launch. 

## Starting the project

This project is maven based and using spring boot runs on the port localhost 8080, there are multiple ways to run the project for example:

```bash
{
mvn install
mvn compile
mvn spring-boot:run
}
```
## Authentication

I used JWT base authentication in order to login call the POST API /auth/login for example:
```bash
curl -X POST "http://localhost:8080/auth/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"password\", \"username\": \"barcelonaadmin\"}"
```

The API will respond with a JWT token for example :<br/>
```bash
{
"token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5ODI4NzM5LCJleHAiOjE2NDk4NTc1Mzl9.UuOMDQ3qZ5p8cDvgBP7BcRNnj0eRc4EN-eP5Ph3VN3Pg41vgMWnGSwOfeO42US8Nd2lU2c9TEt9wzVeK7h8qqA"
}
```
<br/>
All the rest of the endpoints in this project are secured and require authentication using the jwt token for example the endpoint : /api/co2Level/all<br/>

```bash
curl -X GET "http://localhost:8080/api/co2Level/all" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5ODI4NzM5LCJleHAiOjE2NDk4NTc1Mzl9.UuOMDQ3qZ5p8cDvgBP7BcRNnj0eRc4EN-eP5Ph3VN3Pg41vgMWnGSwOfeO42US8Nd2lU2c9TEt9wzVeK7h8qqA"
```
<br/>


## Models and Entities

Entities:<br/>
-City: A city has an id,name, one city can have many district.<br/>
-District: A district has an id,name,a city which is associated to, and the list of Co2Level records.<br/>
-Co2Level:A Co2Level record has an id,level,timestamp and the district which belongs to.<br/>
-UserDetail:Represents a city admin has a username, password, a user belongs to 1 city and can see only the CO2Level records of the districts belonging to the city.<br/>
Models:<br/>
SensorData: A model facilitates the data registration for a Co2Level record.<br/>


## Endpoint Definition
To view all endpoints definition please go on http://localhost:8080/swagger-ui.html to view and tryout on swagger.<br/>
OR<br/>
Link to:[ Endpoints-definition.md](Endpoints-definition.md) to read the API endpoints documentation<br/>
<br/>
Few examples on curl:<br/> 

Save a co2Level of a district: 

```bash 
curl -X POST "http://localhost:8080/api/co2Level" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5ODk4MzAyLCJleHAiOjE2NDk5MjcxMDJ9.ao8bfa9A0bqdORgmcYvVueC3heLUNQ2Zzf1HnEys2-QK8QEdmwu3pU5k7wzzXpVrtBCfRkkGCndR6qLNlQJTFQ" -H "Content-Type: application/json" -d "{ \"cityName\": \"barcelona\", \"districtName\": \"Gràcia\", \"level\": \"60\", \"timestamp\": \"2021-12-11 12:15:20\"}"
```

Get a co2Level of a district: example:

```bash 
curl -X GET "http://localhost:8080/api/co2Level?districtName=Gr%C3%A0cia" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5OTAwMDE0LCJleHAiOjE2NDk5Mjg4MTR9.xvCOMAM6VTxOeYhB0GfFQyNStb3GJJiBrdavx4vg1caqElBvDgsDAj5lUxuqglInqLVOUgY2rb2g98MiV1WbaA"
```

Get all c2Levels of the city:


```bash 
curl -X GET "http://localhost:8080/api/co2Level/all" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5OTAwMDE0LCJleHAiOjE2NDk5Mjg4MTR9.xvCOMAM6VTxOeYhB0GfFQyNStb3GJJiBrdavx4vg1caqElBvDgsDAj5lUxuqglInqLVOUgY2rb2g98MiV1WbaA"
```

## Swagger Usage

Recommended seeing and try all the project endpoints: 
-to access swagger : localhost:8080/swagger-ui.html<br/>
-can easily authenticate by calling the endpoint /auth/login with username and password, the backend will respond with a token <br/>
-click on green authorize button and paste the token to successfully authenticate <br/>
-choose the endpoint to try out and enter the data accordignly <br/>



## Usernames: <br/>
  

```bash 
Default Barcelona admin:barcelonaAdmin  password:password    
```

```bash
Default Wien admin:wienAdmin   password:password 
```



## Default data: <br/>
Username:wienAdmin<br/>
password:password

Username:barcelonaAdmin<br/>
password:password

Barcelona: Gràcia, Eixample<br/>
Wien: Währing, Penzing<br/>
München: Maxvorstadt<br/>

<br/>

## Example of a CO2 reading to a given customer account

Example: user:barcelonaAdmin  password:password<br/>
After authentication with JWT token   <br/>
POST on endpoint /api/co2Level to save the data of a new co2Level reading example:<br/>


```bash 
{
"cityName": "barcelona",
"districtName": "Gràcia",
"level": "60",
"timestamp": "2021-12-11 12:15:20"
}
```

```bash 
curl -X POST "http://localhost:8080/api/co2Level" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXJjZWxvbmFBZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNjQ5ODk4MzAyLCJleHAiOjE2NDk5MjcxMDJ9.ao8bfa9A0bqdORgmcYvVueC3heLUNQ2Zzf1HnEys2-QK8QEdmwu3pU5k7wzzXpVrtBCfRkkGCndR6qLNlQJTFQ" -H "Content-Type: application/json" -d "{ \"cityName\": \"barcelona\", \"districtName\": \"Gràcia\", \"level\": \"60\", \"timestamp\": \"2021-12-11 12:15:20\"}"
```

Note: we suppose that the sensors send the date on the format: yyyy-MM-dd HH:mm:ss<br/>
Each city admin can check CO2 concentration historical data per district/all only for the city in which the admin is associated.


