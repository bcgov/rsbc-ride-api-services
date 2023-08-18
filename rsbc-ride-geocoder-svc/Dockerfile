FROM artifacts.developer.gov.bc.ca/rbe5-images/maven-baseimage:3.8.5-openjdk-17 AS MAVEN_BUILD

COPY pom.xml /build/
COPY . /build/
WORKDIR /build/
RUN pwd
RUN ls -a
RUN mvn clean install -DskipTests


FROM artifacts.developer.gov.bc.ca/rbe5-images/openjdk-basseimage:17.0.2-jdk
WORKDIR /app
RUN curl -k -L -o opentelemetry-javaagent.jar https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
RUN pwd
RUN ls -a
EXPOSE 8080
COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
ENTRYPOINT java -jar -javaagent:/app/opentelemetry-javaagent.jar app.jar