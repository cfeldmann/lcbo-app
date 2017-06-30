# Technology Choices:

I chose to use Java as a programming language because that's what I know best.

I decided to build the app using Spring Framework.  I've never used Spring Framework but it seems to be widely used in the industry so I thought this would be a good opportunity to learn.

I used MongoDB for storing user credentials.  I wanted to use a NoSQL database and found that Spring has support for MongoDB so that made the choice easy.


# Prerequisites

1. MongoDB is installed and running
2. Tomcat is installed and running


# Building the app:

From the LcboApp directory: gradlew war


# Running the app:

1. Deploy build/libs/LcboApp.war to Tomcat
2. Go to http://localhost:8080/LcboApp


# Testing the app:

From LcboApp directory: gradlew tests