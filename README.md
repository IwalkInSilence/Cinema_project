# Football matches ticket shop 
**Implementation of an online store to bye tickets for Football matches with default functionality written in Java.**

## Structure

###### The project has an N-tier structure and consists of the layers:
* Database layer;
* DAO layer (JDBC API);
* Service layer(contains the business logic);
* Layer with controllers.

###### Store clients can perform the following actions on this web-service: 

**Not authenticated** customers can perform

* register on the store's website;
* login;

Store **registered Users** can perform the following actions:

* login;
* add items to their cart;
* delete items from the cart;
* place orders.

**Admins** have such authority:

* view information about registered users;
* view all orders placed at the store;
* add new locations, events and sessions;
* modify exist sessions.

## Technologies

* Java 11
* Maven 3.1.1
* Maven Checkstyle Plugin
* Hibernate
* MySQL
* Javax Servlet API
* Spring Framework
* Spring MVC
* Spring Security
* Apache Tomcat

## To start the project you need:

1. Download and install the JDK
2. Download and install web-server (for example Apache Tomcat)
3. Download and install MySQL. Setup connection properties in **db.properties** file
* user: "your username"
* password: "your password"
* db.url=jdbc:mysql://localhost/*your_db_name*?serverTimezone=EET
4. Run a project

