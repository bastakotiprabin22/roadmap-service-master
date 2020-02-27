Road Way Service Spring Boot Application
This application will help you in finding the direct route or connected routes of two cities with all the necessary logics  required for finding the connected routes if there is no direct path is available. It is been created by using maven archetype from Spring initialzr with swagger been included for documentation

Prerequisites:
==============

Check the city.txt file is under the classpath with the city pair of list data present and load the project by running spring boot appliction.

Implementation:
===============
    1.) Create the SpringBoot project using the https://start.spring.io/  or clone/ download the project and import as maven project.
    2.) Used Java 1.8 version 
    3.) Controller class with sole method as Get Mapping available for finding the connected cities.
    4.) Service class has the actual logic implemented to find the direct route or indirect or no route.
    5.) Test cases written in the Junit by using the webMvcTest and mockMvc 
    6.) To run the application , right click on the project and choose Run as Java application.
    7.) Follow the below instructions for testing 

Testing URLs
============
GET METHOD:
http://localhost:8080/connected?origin=Philadelphia&destination=Boston

For Swagger
============

Start the applcation and try to open the below URL in any browser.. You can use the swagger for getting the information about the API and test the API with the known data.

http://localhost:8080/swagger-ui.html


Code Owners
For bugs/queries please reach out to us -

Name :
Email ID:
