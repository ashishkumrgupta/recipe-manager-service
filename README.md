# Recipe Manager Api

This is web application which allows users to manage their favourite recipes. It provides endpoints to show all
available recipes and the actions to create, update and delete a recipe. API’s is able to retrieve recipes with
following attributes:

1. Indicator if the dish is vegetarian.
2. Date and time of creation (formatted as dd‐MM‐yyyy HH:mm)
3. Indicator displaying the number of people the dish is suitable for.
4. Display ingredients as a list.
5. Cooking instructions.

### Prerequisites:

Before running this application we assume your environment has the following:

1. JRE11
2. Maven installed
3. JCE - java cryptography Extensions
4. Curl or postman for testing
5. Docker
6. Intellij or IDE of your choice

## Folder Structure

```code
  .
  ├── documentation/        # Here you can find the api documentation such as sample curl requests, consumer certs etc.
  ├── src/                  # source code
  └── Dockerfile            # Docker file, which runs the applicaiton on container on port 8443 and https protocall  
  └── pom.xml               # Maven parent pom.xml file, add new dependencies here.
```

# Local Setup

### Build and run application locally which accepts http requests, unlike production configuration

```bash
mvn clean package; mvn spring-boot:run
```

### HealthCheck the application

```bash
curl -v 'http://localhost:8080/recipe-manager/actuator/health'
```

### Check Swagger for API documentation

```
http://localhost:8080/recipe-manager/swagger-ui/index.html#/
```

### Build Docker Image

```
docker build --tag=recipe-manager-api:latest .
```

### Run Docker image locally

```
docker run -p8080:8080 recipe-manager-api:latest
```

# Production Setup

### Run application in Production environment, which accepts only https requests

```bash
mvn clean install;mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Verify the endpoint, Certificates are available in the documentation

```bash
curl -k -v --cert-type pem --cert ./consumer.cer  --key consumer.pkcs8 --noproxy localhost -u "test:test123" 'https://localhost:8443/recipe-manager/actuator/health'

curl -k -v -X DELETE --cert-type pem --cert ./consumer.cer  --key consumer.pkcs8 --noproxy localhost 'https://localhost:8443/recipe-manager/recipes?id=1'

curl -k -v -X POST -H "Content-Type: application/json" -d '{"isVegetarian":true,"suitableFor":3,"ingredients":["tomato","onion"],"instructions":"Thisistestinstuctions"}' --cert-type pem --cert ./consumer.cer  --key consumer.pkcs8 --noproxy localhost 'https://localhost:8443/recipe-manager/recipes'
```

### Refer recipe-manager-api/documentation/ssl-certificate-setup.md for SSL setup related information.