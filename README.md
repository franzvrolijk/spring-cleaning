# Spring Cleaning

A small Spring Boot REST API for managing household chores. It is used as a live coding exercise: the codebase contains a fixed set of intentional bugs and code smells for a candidate to find and fix. The API is intentionally kept simple: no database, no security layer, so the focus stays on Java and Spring fundamentals.

## Exercise

See `interview/tasks.md`. That file tells you what types of issues are in this project and what you need to look into.

> [!IMPORTANT]
> Below steps are for Windows

## What You Need

- OpenJDK 21+ installed and available on your PATH

That is it. Gradle is bundled via the wrapper.

## Running

```powershell
.\gradlew.bat bootRun
```

The API runs on `http://localhost:8080`.


## Build

```powershell
.\gradlew.bat build
java -jar build\libs\my-api-0.0.1-SNAPSHOT.jar
```
