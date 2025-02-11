This is a Spring Boot application that accepts temperature readings in JSON and processes and persists them to a postgres database.
This application also allows the user to calculate the average temperature from a given device within a defined hour.
The result will be returned to 2 decimal places.

To make this application more production ready, I would separate out some of my methods, for example the saveAllReadings
method in my service layer. Unfortunately, this is converting DTOs to an entity and persisting them which is bad design
and doesn't adhere to the single responsibility principle. This also made unit testing difficult. I would also avoid using
some of the Spring Boot annotations in my test class.

This application could also be containerised depending on where it would sit within a wider application.