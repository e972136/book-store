FROM openjdk:11
VOLUME /tmp
EXPOSE 8000
ARG JAR_FILE="./target/book-store-0.0.1.jar"
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.secuirty.egd=file:/dev/./urandom","-jar","/app.jar"]
