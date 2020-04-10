# datasource-proxy-tests-java6

datasource-proxy java6 compatibility tests.

## How things work

- Use java7 to run maven (`mvnw`)
- Create executable jar that runs all tests
- Run the jar with java6.

## IDE Setup

- Import and specify JDK 6.

## Command Line Setup

- Use JDK 7 or above to run maven
- Use JDK 6 to run executable jar

## Running Tests

### From IDE

- Select test package to run tests
- Run `AllTests` test suite
- Run `TestMain` main class
- Run individual test class

### From command line

Assemble executable jar
- `./mvnw clean package`  (with JDK7 or above)

Run tests
- `java -jar target/executable-tests.jar` (with JDK6)

_NOTE:_  
When tests are run from `mvn test`, `JdkVersionTest` skips JDK version check.
(Since tests will NOT run on JDK6)
