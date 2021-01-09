# People10
Code Challenge Assignment


Step 1:  Run below sql DDL for creating table in mysql.

        CREATE TABLE `customer` (
          `id` int NOT NULL AUTO_INCREMENT,
          `firstname` varchar(50) DEFAULT NULL,
          `lastname` varchar(50) DEFAULT NULL,
          `username` varchar(50) NOT NULL,
          `password` varchar(10) DEFAULT NULL,
          `email` varchar(50) DEFAULT NULL,
          `dob` datetime DEFAULT NULL,
          PRIMARY KEY (`username`),
          UNIQUE KEY `id_UNIQUE` (`id`)
        ) 
        
Step 2: clone the repo in local
Steps 3: Build and Run the application.
Step 4: Open the swagger on localhost "http://localhost:8082/customer/swagger-ui.html#!/"
