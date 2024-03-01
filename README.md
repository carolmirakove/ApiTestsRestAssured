# ApiTestsRestAssuredExample

## About
This repository contains example API tests developed with `RestAssured` for demo purposes.
Tests run against the [Dog API](https://dog.ceo/dog-api/), "The internet's biggest collection of open source dog pictures." If you benefit from these tests or the API, there is a [link](https://www.paypal.com/paypalme/dogapi) to buy the API maintainer a dog treat on the project home page.

## Development Guidelines
### Anatomy of a Test
Tests are comprised of three types of activities:
* arrange: set up data, authentication, or navigation needed by the test
* act: make a request
* assert: compare actual to expected results

### Design Principles
* Value readability and maintainability over magic. A one-liner that looks clever today may lack important context a year from today.
* Avoid known test smells:
    * [from thoughtbot](https://thoughtbot.com/upcase/decks/5/results)
    * [from Test Double](https://github.com/testdouble/test-smells/tree/main/smells)
* Agree on a tagging strategy with your team, e.g.,:
    * `:smoke` = fundamental happy-path tests to validate deployments
    * `regression` = all tests to be run before application changes can be merged
    * `:external` = tagging tests that interact with third-party systems is useful in isolating regression tests, especially when a partner service announces a surprise upgrade or architecture change
* Before you add a new dependency to the project, articulate the objectives it is meant to solve. Confirm with your collaborators that the potential cost of the dependency is the best solution. For example, for a more complex API, a JSON library like [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) may be useful. 

## Setup
1. [Install maven](https://maven.apache.org/install.html)
2. [Integrate](https://maven.apache.org/ide.html) maven with your IDE
3. Install dependencies in the `pom.xml` using your IDE menu (e.g., using IntelliJ, right click on the project panel, select Maven, select Reload Project) or using the command line with `mvn install -DskipTests`

## Run Tests
Run individual tests using the caret in your IDE at the test or class level.

Run tests by groups via the `testng.xml` file at the `test` directory root.
