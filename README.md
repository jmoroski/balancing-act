# Balancing Act
## Getting Started
### Installing Balancing Act
1. Prerequisites:
   * [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven](https://maven.apache.org/download.cgi)
   * [node](https://nodejs.org/en/download/) and [yarn](https://yarnpkg.com/lang/en/docs/install/)
   
     _Note:_ This step is optional for just building the application since Maven will pull down the appropriate dependencies, but useful for front end development.
   * A database.  The code is set to use [MySQL](https://www.mysql.com/) by default but it's Hibernate, so with a few quick pom.xml and property file changes, the world is your oyster.  [MySQL in a container](https://hub.docker.com/_/mysql/) is a quick way to be up and running.
2. Get and build the project
   ```bash
   git clone https://github.com/jmoroski/balancing-act.git
   cd balancing-act
   mvn install
   ```
3. (At some point you need to actually set up the database.  #3 should talk about that)

### Running Balancing Act application
1. Run the application by navigating to the backend directory and running tomcat via Maven.
   ```bash
   cd backend
   mvn tomcat7:run-war
   ```
2. From a browser, navigate to http://localhost:8080/