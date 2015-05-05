FROM dockerfile/java:oracle-java8
MAINTAINER haljik

ADD ./build/libs/spring-boot-cluster-examples-0.1.0.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar",\
    "--session.store.type=redis",\
    "--spring.redis.host=${REDIS_PORT_6379_TCP_ADDR}",\
    "--spring.redis.port=${REDIS_PORT_6379_TCP_PORT}"]
