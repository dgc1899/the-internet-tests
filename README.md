# the-internet-tests

Automated UI and API test suite targeting two public demo applications:

- **UI:** [the-internet.herokuapp.com](https://the-internet.herokuapp.com) — exercises common web interaction patterns (alerts, dropdowns, dynamic loading, frames, hovers, login/secure area, windows).
- **API:** [restful-booker.herokuapp.com](https://restful-booker.herokuapp.com) — exercises the Restful Booker CRUD endpoints.

## Tech stack

- Java 11, Maven
- Selenium 4 (UI), REST Assured + Jackson (API)
- TestNG as the test runner, with Hamcrest matchers
- Log4j 2 for logging
- GitHub Actions for pull-request CI

## Project layout

```
src/test/java
├── api/         REST Assured tests, POJOs, and helpers for Restful Booker
├── ui/          Selenium Page Objects and tests for the-internet
└── config/      Shared configuration and TestNG listeners
src/test/resources
├── configuration.properties   Base URLs, browser, environment
└── log4j2.xml                 Logging configuration
postman/        Postman collection for the Restful Booker API
testng-*.xml    TestNG suites (api, functional, regression, smoke)
```

## Running the tests

Run the default suite (`testng-functional.xml`):

```bash
mvn test
```

Run a specific suite by overriding the `suiteFile` property:

```bash
mvn test -DsuiteFile=testng-smoke.xml
mvn test -DsuiteFile=testng-regression.xml
mvn test -DsuiteFile=testng-api.xml
```

## Configuration

Runtime settings are defined in `src/test/resources/configuration.properties`:

| Property     | Description                              | Default                                |
|--------------|------------------------------------------|----------------------------------------|
| `baseUrl`    | Root URL for UI tests                    | `https://the-internet.herokuapp.com`   |
| `apiBaseUrl` | Root URL for API tests                   | `https://restful-booker.herokuapp.com` |
| `browser`    | Browser used by Selenium (`firefox`, …)  | `firefox`                              |
| `env`        | Environment label used in logs/reports   | `test`                                 |

## Continuous integration

Pull requests to `main` trigger the workflow in `.github/workflows/OnPullRequest.yml`, which builds the project and runs the test suite.
