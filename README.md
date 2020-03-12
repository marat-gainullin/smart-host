# Smart host trial application
Smart host trial application is a sample hotelier resources manager solution.

## Requirements
To build and run this project you need JDK 11.0.1+, Git 2.15.1+ and Gradle 6.1.1+ (If you don't want to install Gradle manually, then use `gradlew` commands).

## Building
To build the project from source, use the following commands:
```
git clone https://github.com/marat-gainullin/smart-host.git
cd smart-host
gradlew assemble
```

## Running
To run the SpringBoot application, use the following command:
```
gradlew bootRun
```
This will start a web server, listening on port 8080.
To plan rooms occupation, navigate to `http://localhost:8080/occupations/optimal?availablePremium=3&availableEconomy=3` with with your favorite web browser.
Fill free to change values of `availablePremium` and `availableEconomy` parameters to plan various occupations.

## Testing
To test the the project, use the following command:
```
gradlew test
```

To review test code coverage report, use the following command:
```
gradlew jacocoTestReport
```
and navigate to `build/reports/jacoco/index.html` with your favorite web browser.