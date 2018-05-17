package ms.db.controller;

import ms.db.resources.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/people")
    public void run(@RequestBody Person personJson) throws Exception {

        // example of jdbc template usage for INSERT / UPDATE / DELETE statements,
        // using a RowMapper to convert the selected records into objects.
        jdbcTemplate.update("INSERT INTO PERSON(FIRST_NAME, LAST_NAME) VALUES (?, ?)", new Object[]{
                personJson.getFirstName(), personJson.getLastName()
        });

    }

    @GetMapping("/people")
    public List<Person> getPeople() {

        // example of jdbc template use for SELECT statements, using a RowMapper
        return jdbcTemplate.query("SELECT FIRST_NAME, LAST_NAME, ID FROM PERSON",
                PersonController::mapPersonRecordToPersonObject);
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable long id) {

        // example of jdbc template use for SELECT statements with a WHERE clause,
        // using a RowMapper to convert the selected record into an object.
        List<Person> people = jdbcTemplate.query("SELECT FIRST_NAME, LAST_NAME, ID FROM PERSON WHERE ID = ?",
                new Object[]{id}, PersonController::mapPersonRecordToPersonObject);
        return ((people == null) || people.isEmpty()) ? null : people.get(0);
    }

    private static Person mapPersonRecordToPersonObject(ResultSet resultSet, int i) throws SQLException {
        String firstName = resultSet.getString("FIRST_NAME");
        String lastName = resultSet.getString("LAST_NAME");
        long id = resultSet.getLong("ID");
        return new Person(firstName, lastName, id);
    }

}
