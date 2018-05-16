package ms.db.controller;

import ms.db.resources.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    public void run(@RequestBody Person person) throws Exception {
        jdbcTemplate.update("INSERT INTO PERSON(FIRST_NAME, LAST_NAME) VALUES (?, ?)", new Object[]{
                person.getFirstName(), person.getLastName()
        });

    }

    @GetMapping("/people")
    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT FIRST_NAME, LAST_NAME, ID FROM PERSON",
                PersonController::mapPerson);
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable long id) {
        List<Person> people = jdbcTemplate.query("SELECT FIRST_NAME, LAST_NAME, ID FROM PERSON WHERE ID = ?",
                new Object[]{id}, PersonController::mapPerson);
        return ((people == null) || people.isEmpty()) ? null : people.get(0);
    }

    private static Person mapPerson(ResultSet resultSet, int i) throws SQLException {
        String firstName = resultSet.getString("FIRST_NAME");
        String lastName = resultSet.getString("LAST_NAME");
        long id = resultSet.getLong("ID");
        return new Person(firstName, lastName, id);
    }

}
