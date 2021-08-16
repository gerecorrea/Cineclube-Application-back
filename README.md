# Cineclube-Application-back

# Requisitos:
- Java 11+;
- Postgresql 9+;

# Valores de configurações padrão esperados para o banco:
- url: jdbc:postgresql://localhost:5432/cineclube_app
- username: postgres
- password: postgres
- Se necessário, modificar o application.yml url, username e password do banco para variáveis e executar no CMD:


# Execução:
- mvn clean install
- java -jar ./target/cineclube-0.0.1-SNAPSHOT.jar
