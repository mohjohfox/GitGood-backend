# Setup for local development

## Database connection
For the development a MariaDB is needed.
In order to be able to establish a connection from the 
local development environment to the MariaDB the following 
configurations must be made:
1. create `application-local.properties` file in the **resources** directory with the same 
content as in `application.properties`


2. Replace the following parameters with the data from the MariaDB you set up

   `spring.datasource.url = jdbc:mysql://EXAMPLE_URL:PORT/DATABASE_NAME`

   `spring.datasource.username = EXAMPLE_USERNAME`

   `spring.datasource.password = EXAMPLE_PASSWORD`


3. Setup the local spring profile in the VM-options in the start configuration

   `-Dspring.profiles.active=local`


4. Run the init.sql script in the database directory to generate the required database schema.

# Build

`mvn clean install`

or skipping tests with

`mvn clean install -Dmaven.test.skip=true`

# Build and package

`mvn package`

# Run tests

1. Run all tests: `mvn clean test`
2. Run specific tests: `mvn clean test -Dtest=your.package.TestClassName`
