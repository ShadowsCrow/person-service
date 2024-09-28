# Usar uma imagem base do OpenJDK (Java 17)
FROM openjdk:17-jdk-alpine

# Definir um argumento para o arquivo JAR
ARG JAR_FILE=target/*.jar

# Copiar o arquivo JAR gerado pelo Maven ou Gradle para dentro do contêiner
COPY ${JAR_FILE} app.jar

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
