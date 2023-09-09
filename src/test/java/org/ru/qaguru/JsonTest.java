package org.ru.qaguru;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ru.qaguru.model.Person;

import java.io.InputStream;
import java.util.List;

public class JsonTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка Json")
    public void jsonParsingTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("person.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Person> person = objectMapper.readValue(stream, new TypeReference<>() {
            });

            Person firstPerson = person.get(0);
            Assertions.assertThat(firstPerson.getId()).isEqualTo(1);
            Assertions.assertThat(firstPerson.getFirstName()).isEqualTo("Dmitrii");
            Assertions.assertThat(firstPerson.getLastName()).isEqualTo("Kalinin");

            Person secondPerson = person.get(1);
            Assertions.assertThat(secondPerson.getId()).isEqualTo(2);
            Assertions.assertThat(secondPerson.getFirstName()).isEqualTo("Borya");
            Assertions.assertThat(secondPerson.getLastName()).isEqualTo("Ivanov");

        }
    }
}