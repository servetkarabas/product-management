
Default port
--------------
8095

To Run the service
-----------------
./gradlew bootRun


Clean Build
------------
./gradlew clean build


Swagger address
-------------
http://localhost:8095/swagger-ui.html


Embedded DB Address
--------------
http://localhost:8095/h2-console


Postman Collection
------------------------
product-postman-collection.json





Definition of Problem
----------------------
Product
Create a ​RESTful​ ​application using Java and ​Spring Boot​.
It should have an API supporting the basic CRUD operations for products:
● Create a new product
● Retrieve a list of all products
● Update a product
● Delete a product (​soft deletion​)
Each product should have a SKU (unique id), a name, a price and date it was created.