[![Build Status](https://travis-ci.com/Lukas-bjornvad/Lukas14-01-2020.svg?branch=master)](https://travis-ci.com/Lukas-bjornvad/Lukas14-01-2020)
Instructions
==================
Preconditions:
In order to use this code, you should have a local developer setup + a "matching" droplet on Digital Ocean as described in the 3. semester guidelines^

To set up the project backend to work on your machine and pipeline, change the following:
- pom.xml : Domain name
- config.properties : names of both databases
- .travis.yaml : name of test database
- Travis, environment variables: REMOTE_USER + REMOTE_PW
- rest, @OpenAPIDefinition: Local and remote server url for openapi.
- CorsResponseFilter, Access-Control-Allow-Origin: Your frontend deployment
- if you want user functionality: run the createUserRoles.sql script on your non-test database

For instructions on how to use the API see the following openapi decription:
https://helvedesmaskine.dk/TeamOne-CA3/openapi/

^ This project contains two major documentation files: 
 - [First time users - getting started](README_proof_of_concept.md)
 - [How to use for future projects](README_how_to_use.md)
