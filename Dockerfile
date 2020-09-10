FROM openjdk:11

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} primes.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","/primes.jar"]
