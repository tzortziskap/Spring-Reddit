FROM maven:3-eclipse-temurin-8-alpine
RUN addgroup app && adduser -S -G app app
RUN mkdir /app && chown app:app /app
USER app
WORKDIR /app
COPY . .
RUN mvn clean package
EXPOSE 8080
CMD ["java","-jar", "target/spring-reddit-0.0.1.jar"]
#docker build -t spring-reddit-image .
#docker run --name spring-reddit-clone-mysqldb --network mysql -e MYSQL_ROOT_PASSWORD=2846 -e MYSQL_DATABASE=spring-reddit-clone -d mysql:8
#docker run -p 8080:8080 --name spring-reddit --network mysql -it spring-reddit-image
