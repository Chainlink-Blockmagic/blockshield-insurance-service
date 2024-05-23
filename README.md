# Blockshield Insurance Service

This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/).
Feel free to contact us for further questions.

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
gradlew clean build
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./build/libs/nfe-service-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
gradlew bootBuildImage --imageName=br.com.i3focus.nfe/nfe-service
```

## Running on Heroku
https://www.tabnews.com.br/jjeanjacques10/spring-boot-microservices-monorepo-heroku

## Requests

### Get Assets
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets'
```

### Get Only Active Assets 
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets?activeOnly=true'
```

### Get Assets By Wallet
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets/wallet/0xE5435Db2b26a59083788cA861e7f86CF7338CF64'
```

### Get Assets By ID
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets/ab9b9a10-a868-4f41-86d7-bbcb640e163f'
```

### Validating if Asset Is Settled
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets/PRECATORIO2024/settled'
```

### Inactivate Assets
```shell
curl --location --request PATCH 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets/8a7f62f0-ad94-4493-966e-f47745eee457/inactivate'
```

### Create Assets
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Precatorio2024",
    "symbol": "Precatorio2024",
    "rating": "AA",
    "description": "Precatório de 2024",
    "observation": "Em caso de falha de liquidação o valor pago será o total investido menos o valor do seguro.",
    "tokenizationPlatform": "MB",
    "active": true,
    "totalSupply": 1000000,
    "price": {
        "unitaryValue": 100.00,
        "yield": "15",
        "currency": "BRL",
        "validity": {
            "from": "2024-05-22T00:00:00-03:00",
            "until": "2024-06-22T23:59:59-03:00"
        }
    },
    "dueDate": "2024-06-22"
}'
```

### Get Transactions By Wallet
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/transactions/wallet/0xE5435Db2b26a59083788cA861e7f86CF7338CF64'
```

### Create Transactions
```shell
curl --location 'https://blockshield-insurance-service-d541038b7771.herokuapp.com/api/v1/assets'
```

## Further readings

* [Gradle user manual](https://docs.gradle.org/)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data MongoDB reference](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/)  
