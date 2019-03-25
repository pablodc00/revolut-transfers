# Revolut Backend Test

## Project specs
Java 8
JAX-RS with Jersey implementation
Maven 3


## How TO:
- Run the App:
```
mvn exec:java
```

- Run Test Cases:
```
mvn test
```


## Endpoints

### 1) GET all users
http://localhost:8080/revolut/api/v1/users

### 2) GET an user
http://localhost:8080/revolut/api/v1/users/{userId}

### 3) GET all transfers
http://localhost:8080/revolut/api/v1/transfers

### 4) GET a transfer
http://localhost:8080/revolut/api/v1/transfers/{transferId}

### 4) POST a transfer
http://localhost:8080/revolut/api/v1/makeTransfer/{transferId}
Payload:
Transfer Object in JSON format:
String senderId
String receiverId
Double amount
Date current date








