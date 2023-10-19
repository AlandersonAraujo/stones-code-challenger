FROM maven:3.8.5-openjdk-17 AS deps

RUN mkdir /project
COPY . /project
WORKDIR /project

RUN mvn clean package

FROM openjdk:17 as runner

ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir /app
ARG --from=deps JAR_FILE=/project/target/stone-0.0.1-SNAPSHOT.jar
COPY --from=deps ${JAR_FILE} /app/app.jar
WORKDIR /app

CMD java $JAVA_OPTS -jar app.jar