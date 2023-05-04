FROM openjdk:11
ADD ./target/blogging-0.0.1-SNAPSHOT.jar /usr/src/blogging-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENV spring.datasource.url = jdbc:postgresql://localhost:5432/myblog?useSSL=false&serverTimezone=UTC
ENV spring.datasource.username = postgres
ENV spring.datasource.password = test123
ENTRYPOINT ["java","-jar", "blogging-0.0.1-SNAPSHOT.jar"]