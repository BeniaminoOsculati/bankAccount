# 💰 **Bank Account** 💰

Implementation of a bank account with [Hexagonal architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))

![archi-hexa](./assets/hexa-schema.png)

The idea of this project is to have services exposed that can be used to do bank operations (deposit, retreat) and to consult the balance of the account and the relatives transactions.
This type of operations can be done only from pre enrolled users, that are saved into our data collection.

# Structure

The project is structured in 3 different layers:
- presentation layer: represent the diving part that expose the interface to handle the services contained into the logic of the Hexagonal architecture (main folder)
- business layer: the logical part of the project, into this layer we can find all the business logics provided from the project (business folder)
- integration layer: represent the driven part that provides us all the data collected from the application and all the methods to do operation with that (driven folder)

For simplicity the presentation part is located directly into the main folder (bankAccount) where we can found the main class BankAccountApplication.

## Service exposed

The presentation layer provides this services

```
1. Get bankAccount balance
2. Get transactions
3. Deposit
4. Get money
```

To call this services is needed a userId that is associated to an existing user

This methods can be used and tested in 2 different ways:
- using the presentation layer exposed from the application, with the related ports (method declared into the BankAccountPorts interface)
- calling the API's exposed from the rest controller

## Tests
Into the folder /test we can find a full set of tests that, with mock logic, provide us examples with real use cases and the relative attended responses.

The tests are organized in folder with the same logic of the application tree (presentation, business, integration) and tests all the different layers of the application, in order to have full coverage of the project and also that every single service works good himself.

# How to use it
Into the main folder there is the BankAccountApplication class that is the main class of our project. To start the project we must only start the main of this class.

As first thing the rest controller will start and listen to the port 8080 for the API requests.

After that the system asks to choose if test also a local simulation with the data of the user1 or not. This service is provided in order to test the same services exposes with 2 different versions (application side, API side).
For the local simulation test is possible to do all the 4 operations only for the user1, this is only a test.

## Data
As written in the documentation the application use an association of local data to manage all the application, this type of solution helps us to have a portable application that works with all the environments and that can be tested and validated with real pre enrolled data.

The following data represents the initial situation when the application is started:

```
User1:
    User: {
        id: 1, 
        name: "User1",
        surname: "User1",
        email: "user1@mail.com",
        birthday: date of application starting
    }
    
    BankAccount: {
        id: 1,
        amount: 0,
        transactions: []
    }

User2:
    User: {
        id: 2, 
        name: "User2",
        surname: "User2",
        email: "user2@mail.com",
        birthday: date of application starting
    }
    
    BankAccount: {
        id: 2,
        amount: 100,
        transactions: []
    }

User3:
    User: {
        id: 3, 
        name: "User3",
        surname: "User3",
        email: "user3@mail.com",
        birthday: date of application starting
    }
    
    BankAccount: {
        id: 3,
        amount: 50,
        transactions: []
    }
```

With this data is possible to test all the 4 methods exposed and the relative errors.
For more details, to test better the application, please check the tests written from the developer.
