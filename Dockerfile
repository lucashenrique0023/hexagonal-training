# Usar a imagem base do OpenJDK
FROM openjdk:23-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Comando para executar a aplicação
CMD ["java", "-jar", "target/hexagonal-0.0.1.jar"]