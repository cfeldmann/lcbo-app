# lcbo-app

This app queries lcboapi.com for LCBO store info and displays it to the user.


## Prerequisites:

1. MongoDB is installed and running
2. Tomcat is installed and running
3. Deployment machine is connected to the internet

## Building:

From the lcbo-app directory: gradlew war


## Running:

1. Deploy build/libs/LcboApp.war to Tomcat
2. Go to http://localhost:8080/LcboApp


## Testing:

From lcbo-app directory: gradlew test


## Compatibility Information
This app was developed using:
- JDK 1.8.0_121

This app was tested on:
- Windows 10
- Tomcat 8.5.15
- MongoDB 3.4.4
