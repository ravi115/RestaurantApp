# RestaurantApp
This application exposes the **GET API** of Restful web services and provides **_search interface_**.
In this application we will be searching **restaurant details** such as *location, restaurant name, phone number etc* based on our **_cuisine choice_**.
for example, if we search restaurant details for **_north indian cuisine_** then this application should give us **list of restaurants**.

### Building environment of this application.
- Eclipse IDE (Oxygen beta version)
- Apache Tomcat Server-9.0 
- MySQL Database -5.6
- Maven Project Framwork- 4.0.0

### Important Library Used.
- mysql-connector-java- 6.0.5
- commons-configuration2- 2.0
- junit framwork- 4.10
- Jersey framwork- 1.19

### Instructions to run the application.
```diff
+before running the application we have to configure the database on client's machine and populate some data.
```
### Configure database. 
   1. open MySQL command line on your machine. if you don't have then you can download it form here [click here](https://dev.mysql.com/downloads/installer/).
   2. [click here](https://github.com/ravi115/RestaurantApp/blob/master/SQL%20Instruction%20set/MySQL-Instruction.txt) if you need any details to open MySQL command line.
   3. Look for **InsertQuery.sql** file [click here](https://github.com/ravi115/RestaurantApp/blob/master/SQL%20Instruction%20set/InsertQuery.sql).
   4. copy the whole content (ctrl+A) and paste to MySQL cmd.
   5. if we won't face any error in the above steps, then database configuration and populating of data is done successfully.

### To run this application follow any of these below steps.
 ##### step-1: 
 1. we can deploy this application on tomcat server directly using war file. [click here](https://github.com/ravi115/RestaurantApp/tree/master/war%20file) to get war file.
 2. we can change _database property_ in appConfig.xml file.
 3. since this application exposes only GET API, we can test this application using any browser or postman.
 
 ##### step-2 :
  1. we can clone this repository to our workspace and build this project as a _maven build_.
  2. we can change _database property_ in appConfig.xml file.
  3. _test_ source folder provides junit test cases for this application.
  
  ```diff
-  use these below URI to search restaurant.
  1. http://localhost:8080/RestaurantApp/restaurant/cuisine?type=north indian
                        or
  2. http://localhost:8080/RestaurantApp/restaurant/cuisine/south indian
```
 
**Note: - search _doc folder_ to  get java document of this application**.

========================================================= o End o ==================================================================
