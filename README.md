## Problem Statement

The deprecation of the Implicit Grant and Password (Resource Owner Password) Grant in OAuth 2.1 has led to a need for alternative approaches
within the OAuth 2.0 framework. As the industry transitions to OAuth 2.1, it is crucial to explore new methods to replace these outdated functionalities.

For developers working on their own OAuth2 projects with Spring Security OAuth, the deprecation of these legacy
approaches can present challenges, particularly for first-party Single Page Applications (SPAs) or mobile
applications. For details regarding the differences between Spring Security vs Spring Security OAuth, refer to
[Spring Security OAuth 2.0 Features Matrix](https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Features-Matrix).

## Proposed Solutions

1. **Keep using OAuth 2.0:** Consider persisting with OAuth 2.0 and your current OAuth Authorization Server
   implementation. You can refer to previous examples [here](https://github.com/EthanNguyen132/spring-security-5-oauth-2.0).

2. **Combine Resource Server and Authorization Server Capabilities:** Another option involves integrating the functionalities of the Resource Server and Authorization Server, enabling the return and validation of access tokens from a unified server. However, be aware that this approach might not be optimal for supporting both first-party and third-party applications.

3. **Assess the Necessity of OAuth:** Evaluate whether OAuth is necessary for the project. Consider whether the desired outcomes could be achieved through the utilization of JWT tokens, potentially implemented through a custom Spring Security AuthenticationManager.

4. **Explore Integration with Third-Party SaaS IdP Solutions:** Consider integrating the first-party client/UI app and the Resource Server with third-party SaaS Identity Provider (IdP) solutions like Auth0 (Okta), FusionAuth, or cloud offerings such as Azure AD or AWS Cognito. This option is particularly beneficial if the Resource server is deployed within these cloud environments.

In this repo, we will explore the 2nd option: Combining Resource Server and Authorization Server into a single application using Spring Security 6

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven
- MySQL server with schema named "jwtdemo"

## Build the Application

To build the Spring Boot application and create a JAR file, run the following Maven command in the project directory:

```
mvn spotless:apply clean package
```

## Run the Application

To run the Spring Boot application as executable jar file, use the following command:

```
java -jar target/jwt-demo-1.0.0.jar
```

To run the Spring Boot application with mvn plugin (mvn package not required)

```
mvn spring-boot:run
```

