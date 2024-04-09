
# HOW TO GENERATE VALID SIGNATURE FOR STREAMAX API

## Compiling signApp.java

```
docker run --rm -v ./java_workspace/:/usr/src/ -w /usr/src/ openjdk:11 javac signapp/SignApp.java
```

## Running signApp

```
docker run --rm -v ./java_workspace/:/usr/src/ -w /usr/src/ openjdk:11 java signapp.SignApp
```