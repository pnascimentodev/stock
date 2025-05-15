FROM ubuntu:latest
LABEL authors="sebastian"

ENTRYPOINT ["top", "-b"]

# Usar uma imagem base com OpenJDK
FROM eclipse-temurin:17-jdk-alpine

# Definir diretório de trabalho
WORKDIR /app

# Copiar o jar da aplicação para dentro do container
COPY target/stock-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
