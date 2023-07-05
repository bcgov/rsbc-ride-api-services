FROM artifacts.developer.gov.bc.ca/rbe5-images/maven-baseimage:3.8.5-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY . /build/
WORKDIR /build/
RUN pwd
RUN ls -a
RUN mvn clean install -DskipTests


FROM artifacts.developer.gov.bc.ca/rbe5-images/openjdk-basseimage:17.0.2-jdk
WORKDIR /app
RUN pwd
COPY --from=MAVEN_BUILD /build/target/geocodersvc-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "geocodersvc-0.0.1-SNAPSHOT.jar"]