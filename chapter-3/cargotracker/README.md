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

##  TEST CASE

The test case is the following:

   - A cargo is booked to be delivered from Hong Kong to New York with the delivery deadline of September 28th of 2023.
   - Based on the specifications, the Cargo is routed accordingly by assigning an itinerary.
   - The Cargo is handled at the various ports of the itinerary and is finally claimed by the customer.
   - The customer can track the cargo at any point of time with a unique Tracking Number.

## MODULES

Booking Module:

      This module takes care of all the operations associated with the booking of the Cargo.

Routing Module:

      This module takes care of all the operations associated with the routing of the Cargo.

Tracking Module:

      This module takes care of all the operations associated with the tracking of the Cargo.

Handling Module:

      This module takes care of all the operations associated with the handling of the Cargo.


## JSON Request ->

Cargo Booking http://localhost:9080/cargotracker/serviceapi/cargobooking)

------------------------------------------------------------------------------------------------------------------------

{

   "bookingAmount": 100,

   "originLocation": "CNHKG",

   "destLocation": "USNYC",

   "destArrivalDeadline": "2023-09-28"

}

This returns a unique "Booking ID" which should be put into all request with the placeholder <<BookingId>>

Cargo Routing (http://localhost:9080/cargotracker/serviceapi/cargorouting)

-----------------------------------------------------------------------------------------------------------------------

{

   "bookingId": "<<BookingId>>"

}

Cargo Handling (http://localhost:9080/cargotracker/serviceapi/cargohandling)

------------------------------------------------------------------------------------------------------------------------

Run in Sequence

Received at port:

{

   "bookingId": "<<BookingId>>",

   "unLocode": "CNHKG",

   "handlingType": "RECEIVE",

   "completionTime": "2023-08-23",

   "voyageNumber": ""

}

Loaded onto carrier:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "CNHKG",

   "handlingType" : "LOAD",

   "completionTime": "2023-08-25",

   "voyageNumber" : "0100S"

}

Unloaded:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "CNHGH",

   "handlingType" : "UNLOAD",

   "completionTime": "2023-08-28",

   "voyageNumber" : "0100S"

}

Loaded onto next carrier:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "CNHGH",

   "handlingType" : "LOAD",

   "completionTime": "2023-09-01",

   "voyageNumber" : "0101S"

}

Unloaded:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "JNTKO",

   "handlingType" : "UNLOAD",

   "completionTime": "2023-09-10",

   "voyageNumber" : "0101S"

}

Loaded onto next carrier:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "JNTKO",

   "handlingType" : "LOAD",

   "completionTime": "2023-09-15",

   "voyageNumber" : "0102S"

}

Unloaded:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "USNYC",

   "handlingType" : "UNLOAD",

   "completionTime": "2023-09-25",

   "voyageNumber" : "0102S"

}

Customs:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "USNYC",

   "handlingType" : "CUSTOMS",

   "completionTime": "2023-09-26",

   "voyageNumber" : ""

}

Claimed:

{

   "bookingId" : "<<BookingId>>",

   "unLocode" : "USNYC",

   "handlingType" : "CLAIM",

   "completionTime": "2023-09-28",

   "voyageNumber" : ""

}
