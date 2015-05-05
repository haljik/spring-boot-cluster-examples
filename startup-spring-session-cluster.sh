#!/usr/bin/env bash
./gradlew clean build
docker build -f app.dockerfile -t app .
docker-compose up
