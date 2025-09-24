# !/bin/bash

# Build crudBackend using Maven
cd ./crudBackend
mvn clean package

# Build crudFrontend using npm
cd ../crudFrontend
npm install
npm run build

# Run the project using Docker Compose
cd ..
docker compose up -d
