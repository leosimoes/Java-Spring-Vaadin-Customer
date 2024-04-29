# Spring and Vaadin - Customer
Autor: Leonardo Simões

## Etapas do desenvolvimento
As etapas de desenvolvimento do projeto foram:

1. Criar projeto (no IntelliJ) com:
- Linguagem Java (17);
- Spring Framework (6.2.3);
- Dependências: Vaadin, JPA, H2, DevTools, Lombok.

![Image-01-IntelliJ](images/Image-01-IntelliJ.png)

2. Configurar o banco de dados H2:
- Em `build.gradle` configurar de acordo com o tipo de uso desejado:
    * `implementation 'com.h2database:h2'`;
    * `runtimeOnly 'com.h2database:h2'`;
    * `testImplementation 'com.h2database:h2'`;
- Em `application.properties`:

```properties
# ================================================================
#                   APPLICATION
# ================================================================
spring.application.name=Java-Spring-Vaadin-Customer
# ================================================================
#                   DATASOURCE - H2 DATABASE
# ================================================================
spring.datasource.url=jdbc:h2:mem:proddb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=leo
spring.datasource.password=senha
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```

![Image-03-Terminal-Run](images/Image-02-Terminal-Run.png)

- testar acesso ao console do H2 em `http://localhost:8080/h2/`:

![Image-04-B-ConsoleH2](images/Image-03-B-ConsoleH2.png)

Obs.:
- Por padrão, o usename seria "sa" e a senha "", e o console do h2 estaria desativado.


## Referências
Spring - Guides - Criando UI CRUD com Vaadin:
https://spring.io/guides/gs/crud-with-vaadin
