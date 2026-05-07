# 💰 Bank Account

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/your-repo-url)
[![Java Version](https://img.shields.io/badge/java-17-blue)](https://adoptopenjdk.net/)
[![Spring Boot](https://img.shields.io/badge/spring--boot-3.1.4-brightgreen)](https://spring.io/projects/spring-boot)
[![Coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)](#testing)

## Project Name and Description

**Bank Account** is a sample application that implements a simple bank account system using [Hexagonal Architecture (Ports and Adapters)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)).  
It exposes services for basic bank operations (deposit, withdraw, balance inquiry, and transaction history) for pre-enrolled users. The application is designed for clarity, testability, and portability, using in-memory data for demonstration and testing.

---

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.1.4
- **Maven**: for build and dependency management
- **JUnit**: 4.12
- **EasyMock**: 3.5.1 (for mocking in tests)
- **Spring Web**: for REST API
- **Apache Commons Lang3**: utility library

---

## Project Architecture

The project follows the **Hexagonal Architecture** (Ports and Adapters), structured into three main layers:

```
+-------------------+
| Presentation Layer|  <-- REST API, CLI (main class)
+-------------------+
          |
          v
+-------------------+
|  Business Layer   |  <-- Core business logic, service interfaces/impl
+-------------------+
          |
          v
+-------------------+
| Integration Layer |  <-- Data access, repositories, DTOs
+-------------------+
```

- **Presentation Layer**: Exposes the API and CLI for user interaction (`BankAccountApplication`, REST controllers).
- **Business Layer**: Contains the core logic and service interfaces/implementations.
- **Integration Layer**: Handles data storage and retrieval (in-memory for this demo), DTOs, and repository implementations.

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- (Optional) cURL or Postman for API testing

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo-url/csharp-cpp-java-bank-account.git
   cd csharp-cpp-java-bank-account
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   The application will start and listen on port `8080`.

4. **(Optional) Run with CLI simulation:**
   On startup, you will be prompted to run a local simulation for `user1`. Enter `1` to interact via CLI.

### Basic Usage Example

#### Using the REST API

- **Get Balance**
  ```
  GET http://localhost:8080/bankAccount/{userId}
  ```
- **Get Transactions**
  ```
  GET http://localhost:8080/transactionList/{userId}
  ```
- **Deposit**
  ```
  POST http://localhost:8080/deposit/{userId}?amount=100
  ```
- **Withdraw**
  ```
  GET http://localhost:8080/retreat/{userId}?amount=50
  ```

#### Using the CLI

Follow the prompts in the terminal to deposit, withdraw, check balance, or view transactions for `user1`.

---

## Project Structure

```
.
├── src
│   ├── main
│   │   └── java/com/bankAccount
│   │       ├── BankAccountApplication.java # Main entry point, CLI
│   │       ├── BankAccountHandler.java          # REST Controller
│   │       ├── BankAccountPorts.java            # Ports interface
│   │       ├── BankAccountProvider.java         # Adapter for application usage
│   │       ├── business
│   │       │   ├── adapter
│   │       │   │   ├── BankAccount.java         # Presentation model
│   │       │   │   └── Transaction.java         # Presentation model
│   │       │   ├── service
│   │       │   │   └── BankAccountService.java  # Business logic interface
│   │       │   └── serviceimpl
│   │       │       └── BankAccountServiceImpl.java # Business logic implementation
│   │       ├── driven
│   │       │ ├── record
│   │       │   │   ├── BankAccountDTO.java      # Data transfer object
│   │       │   │   ├── TransactionDTO.java      # Data transfer object
│   │       │   │   └── UserDTO.java             # Data transfer object
│   │       │   ├── repository
│   │       │   │   └── DataRepository.java      # Repository interface
│   │       │   └── repositoryimpl
│   │       │       └── LocalDataRepositoryImpl.java # In-memory repository implementation
│   │       └── business/util
│   │           └── RecordToPresentationConverter.java # DTO to model converter
│   └── test
│       └── java/com/bankAccount
│           ├── TestUtil.java                    # Test utilities and fixtures
│           ├── business/BankAccountDTOServiceImplTests.java
│           ├── driven/LocalDataRepositoryImplTests.java
│           └── presentation
│               ├── controller/BankAccountDTOPortsHandlerTests.java
│               └── provider/BankAccountDTOProviderTests.java
├── pom.xml                                      # Maven configuration
└── README.md
```

---

## Key Features

- **Hexagonal Architecture**: Clean separation of concerns between presentation, business, and integration layers.
- **RESTful API**: Exposes endpoints for:
  - Get account balance (`GET /bankAccount/{userId}`)
  - Get transaction history (`GET /transactionList/{userId}`)
  - Deposit funds (`POST /deposit/{userId}?amount=...`)
  - Withdraw funds (`GET /retreat/{userId}?amount=...`)
- **CLI Simulation**: Optionally interact with the application via command-line for `user1`.
- **In-Memory Data**: Pre-enrolled users and accounts for easy testing and portability.
- **Full Test Coverage**: Comprehensive unit tests for all layers.
- **Error Handling**: Graceful error messages for invalid operations or users.

---

## Development Workflow

- **Branching**: Not specified; recommend using feature branches and pull requests for contributions.
- **Testing**: All new features and bug fixes should include corresponding unit tests.
- **Build**: Use Maven for build, dependency management, and running tests.

---

## Coding Standards

- **Java 17** features and conventions.
- **Layered separation**: Presentation, business, and integration code are strictly separated.
- **DTOs**: Used for data transfer between layers.
- **Logging**: SLF4J for error and info logging.
- **Equality**: `equals` and `toString` methods overridden for domain objects for clarity and testability.

---

## Testing

- **Frameworks**: JUnit 4.12, EasyMock 3.5.1
- **Coverage**: 100% unit test coverage across all layers (presentation, business, integration).
- **Test Organization**: Mirrors main source structure for clarity.
- **Test Utilities**: `TestUtil.java` provides fixtures and helpers for consistent test data.

---

## Additional Resources

- [Hexagonal Architecture (Wikipedia)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/)
- [JUnit Documentation](https://junit.org/junit4/)

---

*For more details, see the [original documentation in README.md](./README.md) and explore the test cases for usage examples and expected behaviors.*