@echo off
IF EXIST ".env" (
    FOR /F "usebackq tokens=* delims=" %%A IN (.env) DO (
        SET "%%A"
        echo SET "%%A"
    )
    echo Environment variables loaded successfully.

    mvnw spring-boot:run
) ELSE (
    echo .env file not found.
    echo.
    echo Create a .env file with the following contents.
    echo MONGODB_CONFIG_PATH=C:/Users/YourUserId/Documents/EnvVars/mongodb-secrets.properties
    echo.
    echo This properties file should contain your URI with password.
    echo custom.mongo.uri=mongodb://myTestUser:myPassword@localhost:27017/inabox?authSource=inabox
)
