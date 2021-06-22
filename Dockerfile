########Maven Build Stage##########
FROM maven:3.6-jdk-11 as builder
WORKDIR /tmp/
COPY pom.xml ./
COPY src ./src/
RUN mvn clean package
RUN java -Djarmode=layertools -jar ./target/*.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder /tmp/dependencies/ ./
COPY --from=builder /tmp/snapshot-dependencies/ ./
COPY --from=builder /tmp/spring-boot-loader/ ./
COPY --from=builder /tmp/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
