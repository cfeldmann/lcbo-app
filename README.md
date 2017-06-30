# lcbo-app

This app queries lcboapi.com for LCBO store info and displays it to the user.


## Prerequisites:

1. MongoDB is installed and running
2. Tomcat is installed and running


## Building:

From the lcbo-app directory: gradlew war


## Running:

1. Deploy build/libs/lcbo-app.war to Tomcat
2. Go to http://localhost:8080/lcbo-app


## Testing:

From lcbo-app directory: gradlew tests