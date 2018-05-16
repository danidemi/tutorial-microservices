Database Backed App
===================

Example of a database backed app.
The app exposes a REST service to manage "people".

## REST

You can create a new person:

    > http -v post http://127.0.0.1:8080/people firstName=John lastName=Doe
    
Get the existing persons:

    > http -v http://127.0.0.1:8080/people
    
Get a person by his or her ID:

    > http -v http://127.0.0.1:8080/people/123
    
## Database Creation

Under `resources/db/migration` there are some `.sql` scripts that gets executed 
in alphabetical order by [Flyway](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-execute-flyway-database-migrations-on-startup).

## Notes

At </h2-console> a DB console is available.
