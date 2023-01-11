## Jakarta EE 8 Implementation using OpenLiberty Server

The implementation of the CargoTracker App has been made using a monolithic approach,
based on OpenLiberty server. I have used following technologies:

- Jakarta v9.1.
- CDI Events as the messaging infrastructure.
- MySQL as Database.
- OpenLiberty server as the runtime which has support for Jakarta v9.1.0


## LAUNCH

1. Before launching the backend service, let's running mysql container:

        docker run -p 3306:3306 --name cargo-mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=cargotracker 
        -e MYSQL_USER=cargotracker -e MYSQL_PASSWORD=cargotracker -d mysql:latest

2. Compile and running the app:

        mvn clean install

        mvn liberty:run
