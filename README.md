Prime Numbers Service
===

The service will check if a provided number in range between `2 and max number` is prime number and find the next prime number after given input number.

##Set-up
1. Checkout the project locally
2. Make sure you have Docker running
3. Build project with Gradle: `./gradlew clean build --stacktrace`
4. Run project: `docker-compose up --build` or (`./gradlew clean build && docker-compose up --build`)
5. Project should be listening on local port`8080`

##Running tests
`./gradlew test` will run basic tests  
Tests created using Cucumber:  
After the project is started you can run the tests with `./gradlew cucumber`


##API documentation
Swagger is used for documenting the project.  
After starting the project you can access the docs here -> [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

##Future development
Improvements that can be done:
1. Implement external cache client (Redis or Memcached) to store the calculated prime numbers
2. Add application metrics using Graphite or Prometheus
3. Add rate limiting
4. Add shared context for cucumber tests, so they can share data between steps