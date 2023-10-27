# sample1
This is a simple e-commerce demo based on springboot framework in Java 11
This application uses H2 memory database, and provide Restful APIs to show
a simple e-commerce scenarios including:
1. adding product to cart.
2. removing product from cart.
3. getting the cart detail.
4. placing an order.

### Build prerequisites:
- JDK: OpenJDK 11 installed
- Set JAVA_HOME, environment variables and add OpenJDK path to system environment variable, make sure java command is working

### Build steps:
1. Git download this repository and in folder "sample1" run command: ```mvnw clean package```
2. Run command to go to "target" folder: ```cd target```
3. start up application: ```java -jar sample1-1.0.0.jar```
4. Application started when you see "ps.demo.WebServerApplication - Started WebServerApplication" message.
5. Open your browser and navigate to the swagger doc page: http://localhost:8080/springdoc/index.html
6. On the swagger doc page, for each API you can click "Try it out" to call the API.
7. The seed data has been initiated in H2 database, so you can use below parameters: 
- userId: 1 to 3
- cartId: 1
- productId: 1 to 17

### Other URLs for reference: 
- Home index page: http://localhost:8080/
- API swagger doc: http://localhost:8080/springdoc/index.html
- OpenAPI in JSON format: http://localhost:8080/springdoc/api-docs
- OpenAPI in YAML format: http://localhost:8080/springdoc/api-docs.yaml
- H2 database console: http://localhost:8080/h2-console

### This application is based on memory H2 database, follow below steps to login to H2 database:
1. In browser open URL: http://localhost:8080/h2-console
2. Saved Settings set to: Generic H2 (Embedded)
3. Driver Class set to: org.h2.Driver
4. JDBC URL set to: jdbc:h2:mem:ecommercedb
5. User Name set to: sa
6. No password so, "Password" leave it empty.
7. Click "Connect" button.
8. You will be able to see the tables in left, and you can run SQL on the right panel.

### This is just a simple demo, there are still a lot can be improved, for now just, I will just stop here:
1. http to https
2. user authentication & authorization
3. data query logic & database sql to optimize
4. Use constructor injection instead of using @Autowired. Unit testing. etc.

### Start with docker:
- docker build -t app:v1 -f Dockerfile .
- docker run --name app --rm -itd -p 8080:8080 app:v1
- Docker build from upper folder: docker build -t app:v1 -f script/Dockerfile .
- Docker stop container: docker stop app
- Remove unused images (careful it will remove base image, like jdk): docker image prune -a 
###
#Mock usage:
1): http://localhost:8080/mock/create-mock to create mock.
2): http://localhost:8080/mock/api/{YourMockUri} to use the created mock.
On the "create-mock" phase, the headers and body content will be parsed by thymeleaf engine.
and the "request" variable will be passed to the script context. So basically you can put the
content as below: {"requestMethod": "[(${request.method})]", "randomStr": "[(${#strings.randomAlphanumeric(8)})]"}
Example to create mock:
curl -X 'POST' \
'http://localhost:8080/mock/create-mock' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"uri": "myrest-api",
"regexMatch": true,
"method": "get",
"status": 200,
"headers": "{\"myresponse-header-str\": \"[(${#strings.randomAlphanumeric(8)})]\"}",
"body": "{\"requestMethod\": \"[(${request.method})]-[(${env.TMP})]\", \"randomStr\": \"[(${#strings.randomAlphanumeric(8)})]-[(${#strings.arrayJoin(#numbers.sequence(1, 5), '\'','\'')})]\"}"
}'

And to use the mock:
curl --location --request GET 'http://localhost:8080/mock/api/myrest-api'