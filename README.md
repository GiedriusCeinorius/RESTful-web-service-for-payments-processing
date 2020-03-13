# RESTful-web-service-for-payments-processing

 Simple RESTful web service for apments processing.

## Prerequisites

1. To run the project, in project directory: mvnw spring-boot::run
2. Open "Postman"

## Running tests

Note: Note that 3 payments (with id's: 1, 2, 3) are already in the system and you can query them, payment with id - 3 is canceled:

**1. To create payment TYPE1 (amount (only applicable for EUR), currency, debtor_iban, creditor_iban, details are required):**

- URI-http://localhost:8087/payments/TYPE1
- Request Method-POST
- Content-Type-application/json 

```	
{
    "amount": 34.44,
    "debtor_iban": "CH93 0076 2011 6238 5295 7",
    "creditor_iban": "CH93 0076 2011 6238 5295 7",
    "currency": "EUR",
	"details": "details"
}	
```	

**2. To create payment TYPE2 (amount (only applicable for USD), currency, debtor_iban, creditor_iban, details are optional):**

- URI-http://localhost:8087/payments/TYPE2
- Request Method-POST
- Content-Type-application/json 

```
{
    "amount": 12.99,
    "debtor_iban": "CH93 0076 2011 6238 5295 7",
    "creditor_iban": "CH93 0076 2011 6238 5295 7",
    "currency": "USD"
}	
```	

**3. To create payment TYPE3 (amount (applicable in both USD and EUR), currency, debtor_iban, creditor_iban, bic_code are required):**

- URI-http://localhost:8087/payments/TYPE3
- Request Method-POST
- Content-Type-application/json 

```
{
    "amount": 132.00,
    "debtor_iban": "CH93 0076 2011 6238 5295 7",
    "creditor_iban": "CH93 0076 2011 6238 5295 7",
    "currency": "EUR",
	"details": "details",
	"bic_code" : "some bic code"
}
```	

**4. To cancel payment:**

- URI-http://localhost:8087/payments/2
- Request Method-DELETE
- Content-Type-application/json 

**5. To query all payments that aren't canceled:**

- URI-http://localhost:8087/payments
- Request Method-GET
- Content-Type-application/json 

**6. To query all payments that aren't canceled and filter them by amount:**

- URI-http://localhost:8087/payments?amount=12.99
- Request Method-GET
- Content-Type-application/json 

**7. Clients country IP and country are logged to logs/spring.log file**

**8. The fact that ecternal service was votified is saved to database and also logged to log/spring.log file**



