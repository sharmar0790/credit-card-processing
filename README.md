# Credit Card Processing

### Requirements
- Java 17
- Maven

### Endpoints:
- For adding the credit card details
  - http://localhost:9090/api/creditCards/add
  - Input Request for this 
  ```
  {
    "cardNumber": "123",
    "name": "Alex",
    "limit": 0
  }
  
  ```
  - Output Response
    - On Success
    ```
    {
    "status": "Created",
    "mesage": "Credit Cards Details are stored successfully.",
    "code": 201
    }
    ```
    - On Failure
    ```
    {
    "status": "Bad Request",
    "mesage": "The Credit Card number you're truing to add is mot valid. Please check it and try again..",
    "code": 400
    }
    ```
- For find all the credit card details
  - http://localhost:9090/api/creditCards/  
  - Output Response
   ```
   [
     {
         "cardNumber": "1234567899",
         "name": "Lokesh",
         "limit": 0
     },
     {
         "cardNumber": "12345678997",
         "name": "Deja",
         "limit": 0
     },
     {
         "cardNumber": "123456789934",
         "name": "Caption",
         "limit": 0
     }
   ]
   ```
  
### Headers:
- Content-Type: application/json


### Validations
- All request and response will/must be in JSON
- Credit Card Number 
  - Must not be null/empty.
  - Must be up to 19 Characters
  - Must follow Luhn 10 Algorithm
  - Must be a numeric string.


### Run the code
- Execute `mvn clean package` from the root of the project
- Then from the same folder execute `java -jar target/credit-card-processing-0.0.1-SNAPSHOT.jar`



### Note
- I am not a front end developer, thus I have developed this test from the back-end side.
- All the security related aspects like who can add or get the details can be implemented on the front-end side.


