FROM maven:3-jdk-8-slim as buildtime

WORKDIR /src

COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

COPY . .
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:8-jre-slim as runtime

WORKDIR /app
COPY --from=buildtime /src/target/ ./

CMD ls ./*.jar | xargs -n1 java -jar
