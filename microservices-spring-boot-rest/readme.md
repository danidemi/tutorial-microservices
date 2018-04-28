# Run

From built jar:

	java -jar microservices-spring-boot-rest-2.0.1.RELEASE.jar --server.port=4040
	java -Dserver.port=4040 -jar microservices-spring-boot-rest-2.0.1.RELEASE.jar
	
From built jar, with remote debug enabled:

    java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
        -jar microservices-spring-boot-rest-2.0.1.RELEASE.jar	
	
From mvn, it seems it is not possible to pass parameters to the forked process:

    mvn spring-boot:run

	

# REST Entrypoints

Tests to be run with __HTTPie__.

    http --verbose POST http://localhost:8080/items description="My Item"

    http --verbose http://localhost:8080/items
    
    http --verbose DELETE http://localhost:8080/items/9824eb16-e637-4a64-b397-367cb6dc6727
