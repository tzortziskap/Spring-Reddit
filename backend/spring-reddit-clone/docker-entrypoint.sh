#!/bin/sh

echo "Waiting for MongoDB to start..."
./wait-for spring-reddit-clone-mysqldb

echo "Starting the server..."
java -jar target/spring-reddit-0.0.1.jar