
#### For starting server

mvn package && java -jar target/Account-1.0-SNAPSHOT.jar

#### For testing server

Get all users:
_GET http://localhost:8080/users_

Transfer 

_POST http://localhost:8080/transfer_

JSON Body

_{
	"fromAccountId": 6, 
	"toAccountId": 3, 
	"amount" : "4"
}_


Also you can glance on ApplicationTest



#### Users and Accounts 

We have 3(id's: 1,2,3) users and 5 accounts(id's: 1,2,3,4,5)

{
      "id": 1,
      "name": "John",
      "accounts": [
        {
          "id": 1,
          "amount": 100000
        },
        {
          "id": 2,
          "amount": 500000
        }
      ]
    },
    {
      "id": 2,
      "name": "Matt",
      "accounts": [
        {
          "id": 3,
          "amount": 1000000
        },
        {
          "id": 4,
          "amount": 1000000
        }
      ]
    },
    {
      "id": 3,
      "name": "Oleg",
      "accounts": [
        {
          "id": 5,
          "amount": 100000
        }
      ]
    }

