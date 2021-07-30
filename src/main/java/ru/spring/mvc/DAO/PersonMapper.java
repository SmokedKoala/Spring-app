package ru.spring.mvc.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.spring.mvc.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * из-за того, что поля класса и столбцы таблицы совпадают, можно не создавать свой
 * RowMapper, а использовать BeanPropertyRowMapper<>(Person.class)
 */
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
}
