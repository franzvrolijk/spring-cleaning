# Spring Cleaning

## What You Need

- OpenJDK 21+ installed and available on your PATH

That is it. Gradle is bundled via the wrapper.

## Running

```powershell
.\gradlew.bat bootRun
```

The API runs on `http://localhost:8080`.

## Exercise

See `interview/tasks.md`. That file tells you what types of issues are in this project and what you need to look into.

## Build

```powershell
.\gradlew.bat build
java -jar build\libs\my-api-0.0.1-SNAPSHOT.jar
```
