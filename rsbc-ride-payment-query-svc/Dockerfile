FROM artifacts.developer.gov.bc.ca/rbe5-images/maven-baseimage:3.8.5-openjdk-17 AS MAVEN_BUILD

COPY pom.xml /build/
COPY . /build/

WORKDIR /build/
RUN pwd
RUN ls -a
RUN mvn package

# FROM openjdk:8-jre-alpine
FROM artifacts.developer.gov.bc.ca/rbe5-images/openjdk-basseimage:17.0.2-jdk

WORKDIR /app
RUN pwd


COPY --from=MAVEN_BUILD /build/jh-etk-paymentsvc/target/jh-etk-paymentsvc.jar /app/
# COPY --from=MAVEN_BUILD /build/target/paymentsvc-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "jh-etk-paymentsvc.jar"]
