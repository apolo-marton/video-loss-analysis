
# HOW TO GENERATE VALID SIGNATURE FOR STREAMAX API

## Compiling signApp.java

```
docker run --rm -v ./java_workspace:/usr/src/myapp -w /usr/src/myapp openjdk:11 javac SignApp.java
```

## Running signApp

```
docker run --rm -v ./java_workspace:/usr/src/myapp -w /usr/src/myapp openjdk:11 java SignApp <APP_ID> <TENANT_ID> <SECRET>
```