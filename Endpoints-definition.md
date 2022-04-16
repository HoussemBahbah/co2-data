## Auth:
**Method:** POST<br/>
**Description:** login authentication using username and password, the method returns a JWT token for authenticating users.<br/>
**Enpoint:** /auth/login<br/>
**Body:**<br/>
{
"password": "string",
"username": "string"
}
## City:
**Method:** GET<br/>
**Description:** findAdminCity, returns the city which the logged in user is associated to.<br/>
**Endpoint:** /api/city<br/>
Body:

**Method:** GET<br/>
**Description:** findByCityName, takes a city name and returns the record in the table city with the name entered.<br/>
**Endpoint** /api/city/<br/>
**Body:** cityName string

**Method:** GET<br/>
**Description:** findAllCities, returns the list of all cities.<br/>
**Endpoint :** /api/city/all<br/>
**Body:**

## Co2Level:
**Method:** POST<br/>
**Description:** Saves CO2 level data in the city in which the logged user is associated and the district chosen, we suppose that the sensors send :cityName,districtName,level,timestamp(format: yyyy-MM-dd HH:mm:ss)<br/>
**Endpoint :** /api/co2Level<br/>
**Body:**
{
**"cityName":** "string",
**"districtName":** "string",
**"level":** "string",
**"timestamp":** "yyyy-MM-dd HH:mm:ss"
}

**Method:** GET<br/>
**Description:** returns the list of all CO2 level records in the districts of the city in which the logged in user is associated.<br/>
**Endpoint** : /api/co2Level/all<br/>
**Body:**

**Method:** GET<br/>
**Description:** /api/co2Level, returns the list of all CO2 level records in the district taken in parameter.<br/>
**Endpoint :** /api/co2Level<br/>
**Body:** districtName *string

**Method:** PUT<br/>
**Description:** updates co2Level record<br/>
**Endpoint :** /api/co2Level<br/>
**Body:** {
"cityName": "string",
"districtName": "string",
"level": "string",
"timestamp": "yyyy-MM-dd HH:mm:ss"
}

***Method:** DELETE<br/>
**Description:** /api/co2Level, delete co2Level record<br/>
**Endpoint :** /api/co2Level<br/>
**Body:** id *integer

## District:
**Method:** POST<br/>
**Description:** Saves a new district in the city in which the logged user is associated to.<br/>
**Endpoint :** /api/district<br/>
**Body:** districtName *string

**Method:** GET<br/>
**Description:** Find a existing district by name.<br/>
**Endpoint :** /api/district<br/>
**Body:** districtName *string

**Method:** GET<br/>
**Description:** Returns the list of district in the city which the logged in user is associated to.<br/>
**Endpoint :** /api/district/cityDistricts<br/>
***Body:**

**Method:** UPDATE<br/>
**Description:** Updates an existing district.<br/>
**Endpoint :** /api/district<br/>
**Body:** districtName *string, newDistrictName *string<br/>

**Method:** DELETE<br/>
**Description:** Delete a district by id<br/>
**Endpoint :** /api/district/{id}<br/>
**Body:** 

