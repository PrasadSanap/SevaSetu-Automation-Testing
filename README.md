# SevaSetu – Automation Testing Framework

A Selenium WebDriver automation testing project for **SevaSetu**, a Cooperative Gig Services Platform. This project automates important user workflows using Java, Selenium WebDriver, TestNG and Maven.

The framework focuses on validating login, registration and booking functionality through browser-based automated tests.

## Project Overview

SevaSetu is a platform that connects customers with verified workers for household and community services.

This repository contains the automation testing implementation developed to validate key application workflows and improve software quality through automated testing.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution and assertions |
| Maven | Dependency and build management |
| ChromeDriver | Chrome browser automation |
| IntelliJ IDEA | Development environment |
| Git & GitHub | Version control |

## Project Structure

```text
SevaSetu-Automation-Testing/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/example/
│   │           └── Main.java
│   │
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java
│           │
│           ├── pages/
│           │   ├── LoginPage.java
│           │   ├── RegistrationPage.java
│           │   └── BookingPage.java
│           │
│           └── tests/
│               ├── LoginTest.java
│               ├── RegistrationTest.java
│               └── BookingTest.java
│
├── testng.xml
├── pom.xml
├── .gitignore
└── README.md
```

## Automation Test Coverage

The framework currently covers the following application workflows:

### 1. Login Testing
- Valid user login
- Invalid login credentials
- Login form validation
- Login-related navigation and outcomes

### 2. Registration Testing
- New user registration
- Registration form validation
- Required field validation
- Invalid input handling

### 3. Booking Testing
- Booking workflow validation
- Booking form interactions
- Required field validation
- Booking-related outcomes

*The exact scenarios and assertions are implemented in the respective TestNG test classes.*

## Framework Architecture

The project follows the **Page Object Model (POM)** design pattern to organize automation code.

- **BaseTest:** Handles WebDriver initialization, browser setup and teardown.
- **Pages:** Stores page locators and reusable page interaction methods.
- **Tests:** Contains TestNG test methods and assertions.
- **testng.xml:** Defines the TestNG test suite configuration.
- **pom.xml:** Manages project dependencies and Maven build configuration.

This structure improves code reusability, maintainability and readability.

## Prerequisites

Before running the project, make sure you have:

- Java JDK compatible with the project configuration (Java 24).
- IntelliJ IDEA or another Java IDE.
- Maven.
- Google Chrome browser.
- Git.

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/PrasadSanap/SevaSetu-Automation-Testing.git
```

### 2. Open the project

Open the cloned project in IntelliJ IDEA and allow Maven to download the required dependencies.

### 3. Configure test credentials

The tests use environment variables for test account credentials.

Configure the following variables in your execution environment:

```text
SEVASETU_TEST_EMAIL=your_test_email
SEVASETU_TEST_PASSWORD=your_test_password
```

Replace the placeholder values with valid test account credentials.

**Security:** Never commit actual passwords, API keys or personal credentials to GitHub.

### 4. Run the automation tests

Execute the complete TestNG suite using Maven:

```bash
mvn clean test
```

Alternatively, run the suite through `testng.xml` in IntelliJ IDEA.

## Test Execution Results

The combined automation suite was successfully executed with the following results:

| Metric | Result |
|---|---:|
| Total automated tests | 14 |
| Passed | 14 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |

**Execution status: Successful**

These results represent the recorded test execution and may vary depending on application availability, test data and environment configuration.

## Application Under Test

**Project:** SevaSetu – Cooperative Gig Services Platform

- Live Application: https://cgpfrontend.vercel.app/
- GitHub Repository: https://github.com/PrasadSanap/Cooperative-Gig-Services-Platform

## Future Improvements

- Implement data-driven testing.
- Add cross-browser testing.
- Integrate automated test reporting.
- Implement explicit wait strategies consistently.
- Integrate automation tests into a CI/CD pipeline.
- Expand test coverage for additional application workflows.

## Author

**Prasad Sanap**

- GitHub: https://github.com/PrasadSanap
- LinkedIn: https://linkedin.com/in/prasad-sanap-bba27b347

---

*This project is developed as a practical learning implementation of software testing and browser automation using Selenium WebDriver, Java, TestNG and Maven.*