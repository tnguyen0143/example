
Purpose
MyRetailApp -- retreive product detail or price by "id" and list of products by "category"

Developed in Eclipse Java EE using tomcat and mysql


RESTful APIs -- GET request and JSON response 
	
	BasePath = /MyRetailApp/api/data

	1. Get price by "id",  {id} = param
		Path = /getPriceById/{id}
		Example = http://localhost:8080/MyRetailApp/api/data/getPriceById/1
	
	2. Get product by "id",  {id} = param
		Path = /getProductById/{id}
		Example = http://localhost:8080/MyRetailApp/api/data/getProductById/1
	
	3. Get products by "category"  {category} = param
		Path = /getProductsByCategory/{category}
		Example = http://localhost:8080/MyRetailApp/api/data/getProductsByCategory/toys


Libraries/Technologies Used:
	1. Tomcat Server 7.0
	2. mySQL Database (5.6.26)
		http://dev.mysql.com/downloads/mysql
	3. Jersey Framework (1.19)
		https://jersey.java.net/download.html
	4. Hibernate (4.3.10)
		http://hibernate.org/orm/downloads/
	5. RestAssured Testing Framework (v2.4.1)
		https://github.com/jayway/rest-assured/wiki/Downloads
		

Included: 
	1. Project source code (MyRetailApp) 
	2. Database/myRetailInventory.sql 
		run the file, it inserts data and grants permission for the a 'test_user'
	3. Database/database.config
		has information user, passowrd, port
	4. readMe.txt
		
	
	