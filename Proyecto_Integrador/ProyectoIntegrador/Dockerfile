FROM openjdk:11-alpine
RUN apk update && apk add maven
WORKDIR /app
COPY . .
RUN mvn package
CMD ["java", "-jar", "target/demohib2-0.0.1-SNAPSHOT.jar"]
