# foreign-exchange-rates

Please keep the below tools installed before running the application.

```bash
  JDK17
  Maven
```


Build the project from project's base directory via below coomand.

```bash
mvn clean install
```

Run the project using below command

```bash
mvn spring-boot:run
```

APIs

```bash
http://localhost:8080/api/v1/fx(?targetCurrency=<currencyName>)
http://localhost:8080/api/v1/fx/{targetCurrency}
```

Added code to have simple in-memory cache in fx APIs to skip calls if the data is available for same request params without any TTL. 
