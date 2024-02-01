FROM maven AS package

ENV ROOT=/loveta-spring
RUN mkdir -p $ROOT
WORKDIR $ROOT

ADD . $ROOT

RUN mvn package -f pom.xml

FROM amazoncorretto:17

LABEL authors="lingkai5wu"

COPY --from=package /loveta-spring/target/*.jar /loveta-spring.jar

EXPOSE 8080

ENTRYPOINT java -jar /loveta-spring.jar